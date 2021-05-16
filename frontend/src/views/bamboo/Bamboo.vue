<template>
  <div id="app">
    <div id="scene-container" ref="sceneContainer">bamboo</div>
  </div>
</template>

<script>
import * as THREE from 'three';
// import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { JoyStick } from './toon3d.js';
// import Stats from 'stats.js';
// import * as dat from 'dat.gui';

export default {
  name: 'Bamboo',
  data() {
    return {
      joystick: null,
      container: null,
      player: {},
      scene: null,
      camera: null,
      cameras: null,
      controls: null,
      renderer: null,
      stats: null,
      raycaster: null,
      assetsPath: '../three-assets/',
      anims: ['Walking', 'Walking Backwards', 'Turn', 'Running'],
      animations: {},
      sun: null,
      options: {
        assets: [
          '../three-assets/images/nx.jpg',
          '../three-assets/images/px.jpg',
          '../three-assets/images/ny.jpg',
          '../three-assets/images/py.jpg',
          '../three-assets/images/nz.jpg',
          '../three-assets/images/pz.jpg',
        ],
        oncomplete: function() {
          this.init();
          this.animate();
        },
      },
    };
  },
  components: {},
  methods: {
    init() {
      this.container = this.$refs.sceneContainer;
      this.clock = new THREE.Clock();
      // create scene

      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color(0x00a0f0);
      // this.scene.fog = new THREE.Fog(0xa0a0a0, 700, 4000);

      this.camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 5000);
      // this.camera.position.set(112, 100, 600);

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

      // Model

      const loader = new FBXLoader();
      const game = this;
      const people = ['BeachBabe', 'BusinessMan', 'Doctor', 'FireFighter', 'Housewife', 'Policeman', 'Prostitute', 'Punk', 'RiotCop', 'Roadworker', 'Robber', 'Sheriff', 'Streetman', 'Waitress'];
      const model = people[Math.floor(Math.random() * people.length)];

      loader.load(`${this.assetsPath}fbx/people/${model}.fbx`, function(object) {
        game.player.root = object;

        object.mixer = new THREE.AnimationMixer(game.player.root);
        game.player.mixer = object.mixer;
        game.player.animations = { Idle: object.animations[0] };

        object.name = 'Person';

        object.traverse(function(child) {
          if (child.isMesh) {
            child.castShadow = true;
            child.receiveShadow = true;
          }
        });
        game.player.index = Math.random() > 0.5 ? 0 : 2; //Math.floor(Math.random() * object.children.length);

        const name = model; //object.children[game.player.index].name.substring(3);
        const colors = ['Black', 'Brown', 'White'];
        const color = colors[Math.floor(Math.random() * colors.length)];
        const textureLoader = new THREE.TextureLoader();

        textureLoader.load(`${game.assetsPath}images/SimplePeople_${name}_${color}.png`, function(texture) {
          object.traverse(function(child) {
            if (child.isMesh) {
              child.material.map = texture;
            }
          });
        });
        game.player.object = new THREE.Object3D();
        game.player.object.position.set(3122, 0, -173);
        game.player.object.rotation.set(0, 2.6, 0);
        game.sun.target = game.player.object;

        game.player.object.add(object);
        game.scene.add(game.player.object);

        game.joystick = new JoyStick({
          onMove: game.playerControl,
          game: game,
        });

        game.createCameras();
        game.loadEnvironment(loader);
      });
      this.renderer = new THREE.WebGLRenderer({ antialias: true });
      this.renderer.setPixelRatio(window.devicePixelRatio);
      this.renderer.setSize(window.innerWidth, window.innerHeight);
      this.renderer.shadowMap.enabled = true;
      this.container.appendChild(this.renderer.domElement);

      this.controls = new OrbitControls(this.camera, this.renderer.domElement);
      this.controls.target.set(0, 150, 0);
      this.controls.update();

      window.addEventListener(
        'resize',
        function() {
          game.onWindowResize();
        },
        false
      );
    },
    loadEnvironment(loader) {
      const game = this;
      loader.load(`${this.assetsPath}fbx/town.fbx`, function(object) {
        game.environment = object;
        game.colliders = [];
        game.scene.add(object);
        object.traverse(function(child) {
          if (child.isMesh) {
            if (child.name.startsWith('proxy')) {
              game.colliders.push(child);
              child.material.visible = false;
            } else {
              child.castShadow = true;
              child.receiveShadow = true;
            }
          }
        });

        const tloader = new THREE.CubeTextureLoader();
        tloader.setPath(`${game.assetsPath}images/`);

        var textureCube = tloader.load(['px.jpg', 'nx.jpg', 'py.jpg', 'ny.jpg', 'pz.jpg', 'nz.jpg']);

        game.scene.background = textureCube;

        game.loadNextAnim(loader);
      });
    },
    loadNextAnim(loader) {
      let anim = this.anims.pop();
      const game = this;
      loader.load(`${this.assetsPath}fbx/anims/${anim}.fbx`, function(object) {
        game.player.animations[anim] = object.animations[0];
        if (game.anims.length > 0) {
          game.loadNextAnim(loader);
        } else {
          delete game.anims;
          game.action = 'Idle';
          // game.mode = game.modes.ACTIVE;
          game.animate();
        }
      });
    },
    playerControl(forward, turn) {
      turn = -turn;

      if (forward > 0.3) {
        if (this.player.action != 'Walking' && this.player.action != 'Running') this.action = 'Walking';
      } else if (forward < -0.3) {
        if (this.player.action != 'Walking Backwards') this.action = 'Walking Backwards';
      } else {
        forward = 0;
        if (Math.abs(turn) > 0.1) {
          if (this.player.action != 'Turn') this.action = 'Turn';
        } else if (this.player.action != 'Idle') {
          this.action = 'Idle';
        }
      }

      if (forward == 0 && turn == 0) {
        delete this.player.move;
      } else {
        this.player.move = { forward, turn };
      }
    },
    createCameras() {
      // const offset = new THREE.Vector3(0, 80, 0);
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
      this.player.cameras = { front, back, wide, overhead, collect };
      this.player.cameras.active = this.player.cameras.back;
    },
    getMousePosition(clientX, clientY) {
      const pos = new THREE.Vector2();
      pos.x = (clientX / this.renderer.domElement.clientWidth) * 2 - 1;
      pos.y = -(clientY / this.renderer.domElement.clientHeight) * 2 + 1;
      return pos;
    },
    tap(evt) {
      if (!this.interactive) return;

      let clientX = evt.targetTouches ? evt.targetTouches[0].pageX : evt.clientX;
      let clientY = evt.targetTouches ? evt.targetTouches[0].pageY : evt.clientY;

      this.mouse = this.getMousePosition(clientX, clientY);
    },
    onWindowResize() {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(window.innerWidth, window.innerHeight);
    },
    movePlayer(dt) {
      const pos = this.player.object.position.clone();
      pos.y += 60;
      let dir = new THREE.Vector3();
      this.player.object.getWorldDirection(dir);
      if (this.player.move.forward < 0) dir.negate();
      let raycaster = new THREE.Raycaster(pos, dir);
      let blocked = false;
      const colliders = this.colliders;

      if (colliders !== undefined) {
        const intersect = raycaster.intersectObjects(colliders);
        if (intersect.length > 0) {
          if (intersect[0].distance < 50) blocked = true;
        }
      }

      if (!blocked) {
        if (this.player.move.forward > 0) {
          const speed = this.player.action == 'Running' ? 400 : 150;
          this.player.object.translateZ(dt * speed);
        } else {
          this.player.object.translateZ(-dt * 30);
        }
      }

      if (colliders !== undefined) {
        //cast left
        dir.set(-1, 0, 0);
        dir.applyMatrix4(this.player.object.matrix);
        dir.normalize();
        raycaster = new THREE.Raycaster(pos, dir);

        let intersect = raycaster.intersectObjects(colliders);
        if (intersect.length > 0) {
          if (intersect[0].distance < 50) this.player.object.translateX(100 - intersect[0].distance);
        }

        //cast right
        dir.set(1, 0, 0);
        dir.applyMatrix4(this.player.object.matrix);
        dir.normalize();
        raycaster = new THREE.Raycaster(pos, dir);

        intersect = raycaster.intersectObjects(colliders);
        if (intersect.length > 0) {
          if (intersect[0].distance < 50) this.player.object.translateX(intersect[0].distance - 100);
        }

        //cast down
        dir.set(0, -1, 0);
        pos.y += 200;
        raycaster = new THREE.Raycaster(pos, dir);
        const gravity = 30;

        intersect = raycaster.intersectObjects(colliders);
        if (intersect.length > 0) {
          const targetY = pos.y - intersect[0].distance;
          if (targetY > this.player.object.position.y) {
            //Going up
            this.player.object.position.y = 0.8 * this.player.object.position.y + 0.2 * targetY;
            this.player.velocityY = 0;
          } else if (targetY < this.player.object.position.y) {
            //Falling
            if (this.player.velocityY == undefined) this.player.velocityY = 0;
            this.player.velocityY += dt * gravity;
            this.player.object.position.y -= this.player.velocityY;
            if (this.player.object.position.y < targetY) {
              this.player.velocityY = 0;
              this.player.object.position.y = targetY;
            }
          }
        }
      }
    },
    animate() {
      const game = this;
      const dt = this.clock.getDelta();

      requestAnimationFrame(function() {
        game.animate();
      });
      //  && this.mode == this.modes.ACTIVE 조건 생략
      if (this.player.mixer != undefined) this.player.mixer.update(dt);

      if (this.player.action == 'Walking') {
        const elapsedTime = Date.now() - this.player.actionTime;
        if (elapsedTime > 1000 && this.player.move.forward > 0) {
          this.action = 'Running';
        }
      }

      if (this.player.move !== undefined) {
        this.movePlayer(dt);
        //if (this.player.move.forward>0) this.player.object.translateZ(dt*100);
        //if (this.player.move.forward<0) this.player.object.translateZ(dt*-30);
        this.player.object.rotateY(this.player.move.turn * dt);
      }

      if (this.player.cameras != undefined && this.player.cameras.active != undefined) {
        this.camera.position.lerp(this.player.cameras.active.getWorldPosition(new THREE.Vector3()), 0.05);
        const pos = this.player.object.position.clone();
        pos.y += 300;
        this.camera.lookAt(pos);
      }

      if (this.sun !== undefined) {
        this.sun.position.copy(this.camera.position);
        this.sun.position.y += 10;
      }

      this.renderer.render(this.scene, this.camera);

      if (this.stats != undefined) this.stats.update();
    },
  },
  mounted() {
    this.init();
    this.animate();
  },
  computed: {
    action: {
      get: function() {
        return this.player.action;
      },
      set: function(name) {
        if (this.player.action == name) return;

        const _action = this.player.mixer.clipAction(this.animations[name]);
        _action.time = 0;

        this.player.mixer.stopAllAction();
        this.player.action = name;
        this.player.actionTime = Date.now();

        // _action.fadeIn(0.5);
        _action.play();
      },
    },
  },
  created() {
    window.addEventListener('resize', this.onWindowResize);
  },
  destroyed() {
    window.removeEventListener('resize', this.onWindowResize);
  },
};
</script>

<style scoped>
</style>>
