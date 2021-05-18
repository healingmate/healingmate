<template>
  <div id="app">
    <div id="scene-container" ref="sceneContainer"></div>
  </div>
</template>

<script>
import * as THREE from 'three';
// import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import { JoyStick } from '../../utils/toon3d.js';
// import Stats from 'stats.js';
// import * as dat from 'dat.gui';

export default {
  name: 'Blockland',
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
    };
  },
  components: {},
  methods: {
    init() {
      this.container = this.$refs.sceneContainer;
      this.clock = new THREE.Clock();
      // create scene
      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color(0xa0a0a0);
      this.scene.fog = new THREE.Fog(0xa0a0a0, 700, 4000);

      this.camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 5000);
      this.camera.position.set(112, 100, 600);

      let light = new THREE.HemisphereLight(0xffffff, 0x444444);
      light.position.set(0, 200, 0);
      this.scene.add(light);

      // const shadowSize = 200;
      light = new THREE.DirectionalLight(0xffffff);
      light.position.set(0, 200, 100);
      // light.castShadow = true;
      // light.shadow.camera.top = shadowSize;
      // light.shadow.camera.bottom = -shadowSize;
      // light.shadow.camera.left = -shadowSize;
      // light.shadow.camera.right = shadowSize;
      this.sun = light;
      this.scene.add(light);

      // ground
      var mesh = new THREE.Mesh(new THREE.PlaneBufferGeometry(10000, 10000), new THREE.MeshLambertMaterial({ color: 0x999999, depthWrite: false }));
      // mesh.rotation.x = -Math.PI / 2;
      //mesh.position.y = -100;
      mesh.receiveShadow = true;
      this.scene.add(mesh);

      var grid = new THREE.GridHelper(5000, 40, 0x000000, 0x000000);
      //grid.position.y = -100;
      grid.material.opacity = 0.2;
      grid.material.transparent = true;
      this.scene.add(grid);

      // Model
      const loader = new FBXLoader();

      const game = this;

      loader.load(`${this.assetsPath}fbx/Penguin_1.fbx`, function(object) {
        // object.rotate.x = Math.PI / 2;

        console.log(object);

        object.mixer = new THREE.AnimationMixer(object);
        console.log(object.mixer, 'obj mixer');
        game.player.mixer = object.mixer;
        // game.player.root = object.mixer.getRoot();

        // player.animations이 undefined기 때문에
        // game.player.animations.Idle = {값} 으로 선언할수없음
        game.player.animations = { 'Idle': object.animations[0] };
        game.player.animations['Runnig'] = object.animations[1]
        game.player.animations['Walking'] = object.animations[2]
        console.log(game.player.animations, 'game.player')
        object.name = 'FireFighter';

        object.traverse(function(child) {
          if (child.isMesh) {
            child.castShadow = true;
            child.receiveShadow = false;
          }
        });

        const tLoader = new THREE.TextureLoader();
        tLoader.load(`${game.assetsPath}images/SimplePeople_FireFighter_Brown.png`, function(texture) {
          object.traverse(function(child) {
            if (child.isMesh) {
              child.material.map = texture;
            }
          });
        });

        game.player.object = new THREE.Object3D();

        game.player.object.add(object);
        game.scene.add(game.player.object);

        // game.player.mixer.clipAction(object.animations[0]).play();

        // 둘다 같다
        game.animations.Idle = object.animations[0];
        game.animations.Running = object.animations[1];
        game.animations.Walking = object.animations[2];
        // game.animations['Idle'] = object.animations[0];

        game.joystick = new JoyStick({
          onMove: game.playerControl,
          game: game,
        });

        game.createCameras();
        // game.loadNextAnim(loader);
        game.action = 'Idle'
        game.animate()
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
    loadNextAnim(loader) {
      let anim = this.anims.pop();
      const game = this;
      loader.load(`${this.assetsPath}fbx/anims/${anim}.fbx`, function(object) {
        game.animations[anim] = object.animations[0];
        if (game.anims.length > 0) {
          game.loadNextAnim(loader);
        } else {
          delete game.anims;
          game.action = 'Idle';
          // game.action('Idle');
          game.animate();
        }
      });
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
    playerControl(forward, turn) {
      turn = -turn;
      if (forward > 0.3) {
        if (this.player.action != 'Walking' && this.player.action != 'Running') {
          this.action = 'Walking';
        }
      } else if (forward < -0.3) {
        if (this.player.action != 'Walking Backwards') {
          this.action = 'Walking Backwards';
        }
      } else {
        forward = 0;
        if (Math.abs(turn) > 0.1) {
          if (this.player.action != 'Turn') this.action = 'Turn';
        } else if (this.player.actions != 'Idle') {
          this.action = 'Idle';
        }
      }
      if (forward == 0 && turn == 0) {
        delete this.player.move;
      } else {
        this.player.move = { forward, turn };
      }
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
    createCameras() {
      // const offset = new THREE.Vector3(0, 80, 0);
      const front = new THREE.Object3D();
      front.position.set(112, 100, 600);
      front.parent = this.player.object;

      const back = new THREE.Object3D();
      back.position.set(0, 300, -600);
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

    onWindowResize() {
      var container = document.getElementById('scene-container');
      this.camera.aspect = container.clientWidth / container.clientHeight;
      this.camera.updateProjectionMatrix();

      this.renderer.setSize(container.clientWidth, container.clientHeight);
    },
    // action(name) {
    //   if (this.player.action == name) return;
    //   const _action = this.player.mixer.clipAction(this.player.animations[name]);

    //   _action.time = 0;
    //   this.player.mixer.stopAllAction();
    //   this.player.action = name;
    //   this.player.actionTime = Date.now();

    //   _action.fadeIn(0.5);
    //   _action.play();
    // },

    animate() {
      const game = this;
      const dt = this.clock.getDelta();
      requestAnimationFrame(function() {
        game.animate();
      });

      if (this.player.mixer !== undefined) this.player.mixer.update(dt);
      if (this.player.action == 'Walking') {
        const elapsedTime = Date.now() - this.player.actionTime;
        if (elapsedTime > 1000 && this.player.move.forward > 0) {
          this.action = 'Running';
        }
      }

      if (this.player.move !== undefined) {
        this.movePlayer(dt);
        this.player.object.rotateY(this.player.move.turn * dt);
      }
      if (this.player.cameras != undefined && this.player.cameras.active != undefined) {
        this.camera.position.lerp(this.player.cameras.active.getWorldPosition(new THREE.Vector3()), 0.05);
        const pos = this.player.object.position.clone();
        pos.y += 300;
        this.camera.lookAt(pos);
      }

      // if (this.sun != undefined) {
      //   this.sun.position.x = this.player.object.position.x;
      //   this.sun.position.y = this.player.object.position.y + 200;
      //   this.sun.position.z = this.player.object.position.z + 100;
      //   this.sun.target = this.player.object;
      // }
      this.renderer.render(this.scene, this.camera);
      // if (this.stats != undefined) this.stats.update();
    },
  },
  created() {
    // window.addEventListener('resize', this.onWindowResize);
    window.addEventListener('DOMContentLoaded', this.onWindowResize);
  },
  mounted() {
    this.init();
    this.animate();
  },
  destroyed() {
    // window.removeEventListener('resize', this.onWindowResize);
  },
  computed: {
    action: {
      get: function() {
        return this.player.action;
      },
      set: function(name) {
        if (this.player.action == name) return;
        console.log(this.animations[name], 'this.animations[name]')
        const _action = this.player.mixer.clipAction(this.animations[name]);
        console.log(_action, 'setter의 _action')
        _action.time = 0;

        this.player.mixer.stopAllAction();
        this.player.action = name;
        this.player.actionTime = Date.now();

        // _action.fadeIn(0.5);
        _action.play();
      },
    },
  },
};
</script>
<style scoped>
#scene-container {
  height: 100%;
}
</style>
