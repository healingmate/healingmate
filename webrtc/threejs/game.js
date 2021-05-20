class Game {
  constructor(localInformation) {
    if (!Detector.webgl) Detector.addGetWebGLMessage();

    this.modes = Object.freeze({
      NONE: Symbol("none"),
      PRELOAD: Symbol("preload"),
      INITIALISING: Symbol("initialising"),
      CREATING_LEVEL: Symbol("creating_level"),
      ACTIVE: Symbol("active"),
      GAMEOVER: Symbol("gameover"),
    });
    this.mode = this.modes.NONE;
    
    this.profileImage = localInformation.profileImage
    console.log(this.profileImage)
    this.roomid = localInformation.roomid
    this.container;
    this.player;
    this.cameras;
    this.camera;
    this.scene;
    this.renderer;
    this.animations = {};
    this.assetsPath = "assets/";

    this.remotePlayers = [];
    this.remoteColliders = [];
    this.initialisingPlayers = [];
    this.remoteData = [];

    this.messages = {
      text: ["Welcome to Healing Mate Bamboo Forest", "GOOD LUCK!"],
      index: 0,
    };

    this.container = document.createElement("div");
    this.container.style.height = '100%'
    this.container.style.width = '100%'
    document.body.appendChild(this.container)

    const game = this;
    this.anims = ["Walking", "Turn", "Running"];

    const options = {
      assets: [
        `${this.assetsPath}images/nx.jpg`,
        `${this.assetsPath}images/px.jpg`,
        `${this.assetsPath}images/ny.jpg`,
        `${this.assetsPath}images/py.jpg`,
        `${this.assetsPath}images/nz.jpg`,
        `${this.assetsPath}images/pz.jpg`,
      ],
      oncomplete: function () {
        game.init();
      },
    };

    this.anims.forEach(function (anim) {
      options.assets.push(`${game.assetsPath}fbx/anims/${game.profileImage}_${anim}.fbx`);
    });
    options.assets.push(`${game.assetsPath}fbx/town.fbx`);

    this.mode = this.modes.PRELOAD;

    this.clock = new THREE.Clock();

    const preloader = new Preloader(options);

    window.onError = function (error) {
      console.error(JSON.stringify(error));
    };
  }

  set activeCamera(object) {
    this.cameras.active = object;
  }

  init() {
    this.mode = this.modes.INITIALISING;

    this.camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 10, 200000);

    this.scene = new THREE.Scene();
    this.scene.background = new THREE.Color(0x00a0f0);

    const ambient = new THREE.AmbientLight(0xaaaaaa);
    this.scene.add(ambient);

    const light = new THREE.DirectionalLight(0xaaaaaa);
    light.position.set(30, 100, 40);
    light.target.position.set(0, 0, 0);

    light.castShadow = true;

    const lightSize = 500;
    light.shadow.camera.near = 1;
    light.shadow.camera.far = 500;
    light.shadow.camera.left = light.shadow.camera.bottom = -lightSize;
    light.shadow.camera.right = light.shadow.camera.top = lightSize;

    light.shadow.bias = 0.0039;
    light.shadow.mapSize.width = 1024;
    light.shadow.mapSize.height = 1024;

    this.sun = light;
    this.scene.add(light);

    // model
    const loader = new THREE.FBXLoader();
    const game = this;

    this.player = new PlayerLocal(this);

    this.loadEnvironment(loader);

    this.joystick = new JoyStick({
      onMove: this.playerControl,
      game: this,
    });

    this.renderer = new THREE.WebGLRenderer({ antialias: true });
    this.renderer.setPixelRatio(window.devicePixelRatio);
    this.renderer.setSize(window.innerWidth, window.innerHeight);
    this.renderer.shadowMap.enabled = true;
    this.container.appendChild(this.renderer.domElement);

    window.addEventListener("resize", () => game.onWindowResize(), false);
  }

  loadEnvironment(loader) {
    const game = this;
    loader.load(`${this.assetsPath}fbx/town.fbx`, function (object) {
      game.environment = object;
      game.colliders = [];
      game.scene.add(object);
      object.traverse(function (child) {
        if (child.isMesh) {
          if (child.name.startsWith("proxy")) {
            game.colliders.push(child);
            child.material.visible = false;
          } else {
            child.castShadow = true;
            child.receiveShadow = true;
          }
        }
      });

      const tloader = new THREE.CubeTextureLoader();
      tloader.setPath(`${game.assetsPath}/images/`);

      var textureCube = tloader.load(["px.jpg", "nx.jpg", "py.jpg", "ny.jpg", "pz.jpg", "nz.jpg"]);

      game.scene.background = textureCube;

      game.loadNextAnim(loader);
    });
  }

  loadNextAnim(loader) {
    let anim = this.anims.pop();
    const game = this;
    loader.load(`${this.assetsPath}fbx/anims/${this.profileImage}_${anim}.fbx`, function (object) {
      game.player.animations[anim] = object.animations[0];
      if (game.anims.length > 0) {
        game.loadNextAnim(loader);
      } else {
        delete game.anims;
        game.action = "Idle";
        game.mode = game.modes.ACTIVE;
        game.animate();
      }
    });
  }

  playerControl(forward, turn) {
    turn = -turn;

    if (forward > 0.3) {
      if (this.player.action != "Walking" && this.player.action != "Running") this.player.action = "Walking";
    } else if (forward < -0.3) {
      if (this.player.action != "Walking") this.player.action = "Walking";
    } else {
      forward = 0;
      if (Math.abs(turn) > 0.1) {
        if (this.player.action != "Turn") this.player.action = "Turn";
      } else if (this.player.action != "Idle") {
        this.player.action = "Idle";
      }
    }

    if (forward == 0 && turn == 0) {
      delete this.player.motion;
    } else {
      this.player.motion = { forward, turn };
    }

    this.player.updateSocket();
  }

  createCameras() {
    const offset = new THREE.Vector3(0, 80, 0);
    const front = new THREE.Object3D();
    front.position.set(112, 100, 600);
    front.parent = this.player.object;
    const back = new THREE.Object3D();
    back.position.set(0, 300, -1050);
    back.parent = this.player.object;
    const wide = new THREE.Object3D();
    wide.position.set(178, 139, 1665);
    wide.parent = this.player.object;
    const overhead = new THREE.Object3D();
    overhead.position.set(0, 400, 0);
    overhead.parent = this.player.object;
    const collect = new THREE.Object3D();
    collect.position.set(40, 82, 94);
    collect.parent = this.player.object;
    this.cameras = { front, back, wide, overhead, collect };
    this.activeCamera = this.cameras.back;
  }

  onWindowResize() {
    this.camera.aspect = window.innerWidth / window.innerHeight;
    this.camera.updateProjectionMatrix();

    this.renderer.setSize(window.innerWidth, window.innerHeight);
  }

  updateRemotePlayers(dt) {
    if (this.remoteData === undefined || this.remoteData.length == 0 || this.player === undefined || this.player.id === undefined) return;

    const newPlayers = [];
    const game = this;
    //Get all remotePlayers from remoteData array
    const remotePlayers = [];
    const remoteColliders = [];

    this.remoteData.forEach(function (data) {
      if (game.player.id != data.id) {
        //Is this player being initialised?
        let iplayer;
        game.initialisingPlayers.forEach(function (player) {
          if (player.id == data.id) iplayer = player;
        });
        //If not being initialised check the remotePlayers array
        if (iplayer === undefined) {
          let rplayer;
          game.remotePlayers.forEach(function (player) {
            if (player.id == data.id) rplayer = player;
          });
          if (rplayer === undefined) {
            //Initialise player
            game.initialisingPlayers.push(new Player(game, data));
          } else {
            //Player exists
            remotePlayers.push(rplayer);
            remoteColliders.push(rplayer.collider);
          }
        }
      }
    });

    this.scene.children.forEach(function (object) {
      if (object.userData.remotePlayer && game.getRemotePlayerById(object.userData.id) == undefined) {
        game.scene.remove(object);
      }
    });

    this.remotePlayers = remotePlayers;
    this.remoteColliders = remoteColliders;
    this.remotePlayers.forEach(function (player) {
      player.update(dt);
    });
  }

  getRemotePlayerById(id) {
    if (this.remotePlayers === undefined || this.remotePlayers.length == 0) return;

    const players = this.remotePlayers.filter(function (player) {
      if (player.id == id) return true;
    });

    if (players.length == 0) return;

    return players[0];
  }

  animate() {
    const game = this;
    const dt = this.clock.getDelta();

    requestAnimationFrame(function () {
      game.animate();
    });

    this.updateRemotePlayers(dt);

    if (this.player.mixer != undefined && this.mode == this.modes.ACTIVE) this.player.mixer.update(dt);

    if (this.player.action == "Walking") {
      const elapsedTime = Date.now() - this.player.actionTime;
      if (elapsedTime > 1000 && this.player.motion.forward > 0) {
        this.player.action = "Running";
      }
    }

    if (this.player.motion !== undefined) this.player.move(dt);

    if (this.cameras != undefined && this.cameras.active != undefined && this.player !== undefined && this.player.object !== undefined) {
      this.camera.position.lerp(this.cameras.active.getWorldPosition(new THREE.Vector3()), 0.05);
      const pos = this.player.object.position.clone();
      pos.y += 300;
  
      this.camera.lookAt(pos);
    }

    if (this.sun !== undefined) {
      this.sun.position.copy(this.camera.position);
      this.sun.position.y += 10;
    }

    this.renderer.render(this.scene, this.camera);
  }
}
class Player {
  constructor(game, options) {
    this.local = true;
    let model, colour;

    // const colours = fs.readdir(`${game.assetsPath}images/${game.profileImage}`);
    // console.log("colours",colours);
    // colour = colours[Math.floor(Math.random() * colours.length)];

    if (options === undefined) {
      model = game.profileImage;
    } else if (typeof options == "object") {
      this.local = false;
      this.options = options;
      this.id = options.id;
      model = options.model;
      colour = options.colour;
    } else {
      model = options;
    }
    this.model = model;
    this.colour = colour;
    this.game = game;
    this.animations = this.game.animations;

    const loader = new THREE.FBXLoader();
    const player = this;

    loader.load(`${game.assetsPath}fbx/animal/${model}.fbx`, function (object) {
      object.mixer = new THREE.AnimationMixer(object);
      player.root = object;
      player.mixer = object.mixer;

      object.name = "Animal";

      object.traverse(function (child) {
        if (child.isMesh) {
          child.castShadow = true;
          child.receiveShadow = true;
        }
      });

      const textureLoader = new THREE.TextureLoader();

      textureLoader.load(`${game.assetsPath}images/${model}/${model}.png`, function (texture) {
        object.traverse(function (child) {
          if (child.isMesh) {
            child.material.map = texture;
          }
        });
      });

      player.object = new THREE.Object3D();
      player.object.position.set(3122, 0, -173);
      player.object.rotation.set(0, 2.6, 0);

      player.object.add(object);
      if (player.deleted === undefined) game.scene.add(player.object);

      if (player.local) {
        game.createCameras();
        game.sun.target = game.player.object;
        game.animations.Idle = object.animations[0];
        if (player.initSocket !== undefined) player.initSocket();
      } else {
        const geometry = new THREE.BoxGeometry(100, 300, 100);
        const material = new THREE.MeshBasicMaterial({ visible: false });
        const box = new THREE.Mesh(geometry, material);
        box.name = "Collider";
        box.position.set(0, 150, 0);
        player.object.add(box);
        player.collider = box;
        player.object.userData.id = player.id;
        player.object.userData.remotePlayer = true;
        const players = game.initialisingPlayers.splice(game.initialisingPlayers.indexOf(this), 1);
        game.remotePlayers.push(players[0]);
      }

      if (game.animations.Idle !== undefined) player.action = "Idle";
    });
  }

  set action(name) {
    //Make a copy of the clip if this is a remote player
    if (this.actionName == name) return;
    const clip = this.local ? this.animations[name] : THREE.AnimationClip.parse(THREE.AnimationClip.toJSON(this.animations[name]));
    const action = this.mixer.clipAction(clip);
    action.time = 0;
    this.mixer.stopAllAction();
    this.actionName = name;
    this.actionTime = Date.now();

    action.fadeIn(0.5);
    action.play();
  }

  get action() {
    return this.actionName;
  }

  update(dt) {
    this.mixer.update(dt);

    if (this.game.remoteData.length > 0) {
      let found = false;
      for (let data of this.game.remoteData) {
        if (data.id != this.id) continue;
        //Found the player
        this.object.position.set(data.x, data.y, data.z);
        const euler = new THREE.Euler(data.pb, data.heading, data.pb);
        this.object.quaternion.setFromEuler(euler);
        this.action = data.action;
        found = true;
      }
      if (!found) this.game.removePlayer(this);
    }
  }
}

class PlayerLocal extends Player {
  constructor(game, model) {
    super(game, model);

    const player = this;

    const connection = new RTCMultiConnection();

    // 이 부분을 vue에서 roomid 전달받아 {roomid}-threejs-room 이런 형식으로 변경해야함
    const roomid = game.roomid + '-threejs-room';

    console.log(roomid,'되냐 진짜냐!!!!?')

    connection.socketURL = "https://socket.healingmate.kr:8282/";

    connection.socketMessageEvent = 'bamboo-forest';

    connection.session = {
      audio: false,
      video: false,
      data: true,
    };

    connection.mediaConstraints = {
      audio: false,
      video: false
    };

    connection.sdpConstraints.mandatory = {
      OfferToReceiveAudio: false,
      OfferToReceiveVideo: false
    };

    // https://www.rtcmulticonnection.org/docs/iceServers/
    // use your own TURN-server here!
    connection.iceServers = [{
      'urls': [
        'stun:stun.l.google.com:19302',
        'stun:stun1.l.google.com:19302',
        'stun:stun2.l.google.com:19302',
        'stun:stun.l.google.com:19302?transport=udp',
      ]
    }];

    // 채팅에 몇명이나 허용 할것인지
    connection.publicRoomIdentifier = 'bamboo-forest'
    
    connection.openOrJoin(roomid)

    connection.connectSocket(function (socket) {
      player.id = connection.userid;
    });

    setInterval(function(){
      let data = [];
      connection.getAllParticipants().forEach(function(participantId) {
        var user = connection.peers[participantId];
        if (user!==undefined){
          data.push({
            id: user.userid,
            model: user.extra.model,
            colour: user.extra.colour,
            x: user.extra.x,
            y: user.extra.y,
            z: user.extra.z,
            heading: user.extra.heading,
            pb: user.extra.pb,
            action: user.extra.action
          });
        }
      });
      game.remoteData = data;
    }, 40);

    window.addEventListener( 'message', e =>{
      if(e.data==="exit"){
        const players = game.remotePlayers.filter(function (player) {
          if (player.id === data.id) {
            return player;
          }
        });
        if (players.length > 0) {
          let index = game.remotePlayers.indexOf(players[0]);
          if (index !== -1) {
            game.remotePlayers.splice(index, 1);
            game.scene.remove(players[0].object);
          }
        } else {
          index = game.initialisingPlayers.indexOf(data.id);
          if (index !== -1) {
            const player = game.initialisingPlayers[index];
            player.deleted = true;
            game.initialisingPlayers.splice(index, 1);
          }
        }
        connection.attachStreams.forEach(function(localStream) {
        localStream.stop()
        })

        connection.closeSocket();
      }
    });
    this.connection = connection;
  }

  initSocket() {
    this.connection.extra = {
      model: this.model,
      colour: this.colour,
      x: this.object.position.x,
      y: this.object.position.y,
      z: this.object.position.z,
      heading: this.object.rotation.y,
      pb: this.object.rotation.x,
      action : "Idle"
    };
  }

  updateSocket() {
    this.connection.model = this.model;
    this.connection.colour = this.colour;
    this.connection.extra.x = this.object.position.x;
    this.connection.extra.y = this.object.position.y;
    this.connection.extra.z = this.object.position.z;
    this.connection.extra.heading = this.object.rotation.y;
    this.connection.extra.pb = this.object.rotation.x;
    this.connection.extra.action = this.action;

    this.connection.updateExtraData();
    // var connect = this.connection;
    // connect.getAllParticipants().forEach(function(participantId) {
    //   var user = connect.peers[participantId];
    //   console.log(user);
    // });
  }

  move(dt) {
    const pos = this.object.position.clone();
    pos.y += 60;
    let dir = new THREE.Vector3();
    this.object.getWorldDirection(dir);
    if (this.motion.forward < 0) dir.negate();
    let raycaster = new THREE.Raycaster(pos, dir);
    let blocked = false;
    const colliders = this.game.colliders;

    if (colliders !== undefined) {
      const intersect = raycaster.intersectObjects(colliders);
      if (intersect.length > 0) {
        if (intersect[0].distance < 50) blocked = true;
      }
    }

    if (!blocked) {
      if (this.motion.forward > 0) {
        const speed = this.action == "Running" ? 500 : 150;
        this.object.translateZ(dt * speed);
      } else {
        this.object.translateZ(-dt * 30);
      }
    }

    if (colliders !== undefined) {
      //cast left
      dir.set(-1, 0, 0);
      dir.applyMatrix4(this.object.matrix);
      dir.normalize();
      raycaster = new THREE.Raycaster(pos, dir);

      let intersect = raycaster.intersectObjects(colliders);
      if (intersect.length > 0) {
        if (intersect[0].distance < 50) this.object.translateX(100 - intersect[0].distance);
      }

      //cast right
      dir.set(1, 0, 0);
      dir.applyMatrix4(this.object.matrix);
      dir.normalize();
      raycaster = new THREE.Raycaster(pos, dir);

      intersect = raycaster.intersectObjects(colliders);
      if (intersect.length > 0) {
        if (intersect[0].distance < 50) this.object.translateX(intersect[0].distance - 100);
      }

      //cast down
      dir.set(0, -1, 0);
      pos.y += 200;
      raycaster = new THREE.Raycaster(pos, dir);
      const gravity = 30;

      intersect = raycaster.intersectObjects(colliders);
      if (intersect.length > 0) {
        const targetY = pos.y - intersect[0].distance;
        if (targetY > this.object.position.y) {
          //Going up
          this.object.position.y = 0.8 * this.object.position.y + 0.2 * targetY;
          this.velocityY = 0;
        } else if (targetY < this.object.position.y) {
          //Falling
          if (this.velocityY == undefined) this.velocityY = 0;
          this.velocityY += dt * gravity;
          this.object.position.y -= this.velocityY;
          if (this.object.position.y < targetY) {
            this.velocityY = 0;
            this.object.position.y = targetY;
          }
        }
      }
    }

    this.object.rotateY(this.motion.turn * dt);

    this.updateSocket();
  }
}