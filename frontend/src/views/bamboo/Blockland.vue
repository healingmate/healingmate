<template>
  <div id="app">
    <div id="scene-container" ref="sceneContainer" @click="toggleAnimation">blockland</div>
  </div>
</template>

<script>
// import ViewPort from '@/components/common/ViewPort.vue';
import * as THREE from 'three';
// import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
// import Stats from 'stats.js';
// import * as dat from 'dat.gui';

// var game = {
//   set action(name) {
//     const action = this.player.mixer.clipAction(this.animations[name]);
//     action.time = 0;
//     this.player.mixer.stopAllAction();
//     this.player.action = name;
//     this.player.actionTime = Date.now();
//     this.player.actionName = name;

//     action.fadeIn(0.5);
//     action.play();
//   },

//   get action() {
//     if (this.player === undefined || this.player.actionName === undefined) return '';
//     return this.player.actionName;
//   },
// };
export default {
  name: 'Blockland',
  data() {
    return {
      container: null,
      player: {},
      scene: null,
      camera: null,
      controls: null,
      renderer: null,
      stats: null,
      raycaster: null,
      assetsPath: '../three-assets/',
      anims: ['Pointing Gesture'],
      animations: {},
      action: {
        set action(name) {
          const action = this.player.mixer.clipAction(this.animations[name]);
          action.time = 0;
          this.player.mixer.stopAllAction();
          this.player.action = name;
          this.player.actionTime = Date.now();
          this.player.actionName = name;

          action.fadeIn(0.5);
          action.play();
        },

        get action() {
          if (this.player === undefined || this.player.actionName === undefined) return '';
          return this.player.actionName;
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
      this.scene.background = new THREE.Color(0xa0a0a);
      this.scene.fog = new THREE.Fog(0xa0a0a, 700, 1800);

      this.camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 1, 2000);
      this.camera.position.set(112, 100, 400);

      let light = new THREE.HemisphereLight(0xffffff, 0x444444);
      light.position.set(0, 200, 0);
      this.scene.add(light);

      light = new THREE.DirectionalLight(0xffffff);
      light.position.set(0, 200, 100);
      light.castShadow = true;
      light.shadow.camera.top = 180;
      light.shadow.camera.bottom = -100;
      light.shadow.camera.left = -120;
      light.shadow.camera.right = 120;
      this.scene.add(light);

      // ground
      var mesh = new THREE.Mesh(new THREE.PlaneBufferGeometry(4000, 4000), new THREE.MeshLambertMaterial({ color: 0x999999, depthWrite: false }));
      mesh.rotation.x = -Math.PI / 2;
      //mesh.position.y = -100;
      mesh.receiveShadow = true;
      this.scene.add(mesh);

      var grid = new THREE.GridHelper(4000, 40, 0x000000, 0x000000);
      //grid.position.y = -100;
      grid.material.opacity = 0.2;
      grid.material.transparent = true;
      this.scene.add(grid);

      // Model
      const loader = new FBXLoader();
      const game = this;

      loader.load(`${this.assetsPath}fbx/people/FireFighter.fbx`, function(object) {
        object.mixer = new THREE.AnimationMixer(object);
        game.player.mixer = object.mixer;
        game.player.root = object.mixer.getRoot();

        object.name = 'FireFighter';

        object.traverse(function(child) {
          if (child.isMesh) {
            // child.material.map = null;
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
        game.scene.add(object);
        game.player.object = object;
        // game.player.mixer.clipAction(object.animations[0]).play();
        game.animations.Idle = object.animations[0];
        game.loadNextAnim(loader);

        // game.animate();
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
        console.log(game, 'loadnextanim');
        if (game.anims.length > 0) {
          game.loadNextAnim(loader);
        } else {
          console.log('else', game);
          delete game.anims;
          game.actions = 'Idle';
          game.animate();
          console.log('animate');
        }
      });
    },
    onWindowResize() {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();

      this.renderer.setSize(window.innerWidth, window.innerHeight);
    },

    toggleAnimation() {
      console.log('클릭', this.action);
      if (this.action == 'Idle') {
        this.action = 'Pointing Gesture';
      } else {
        this.action = 'Idle';
      }
    },

    animate() {
      const game = this;
      const dt = this.clock.getDelta();

      requestAnimationFrame(function() {
        game.animate();
      });

      if (this.player.mixer !== undefined) this.player.mixer.update(dt);

      this.renderer.render(this.scene, this.camera);
    },
  },
  mounted() {
    this.init();
    this.animate();
  },
  computed: {},
  created() {
    window.addEventListener('resize', this.onWindowResize);
  },
  destroyed() {
    window.removeEventListener('resize', this.onWindowResize);
  },
};
</script>
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
#scene-container {
  height: 100%;
}
</style>
<style>
html,
body {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
body {
  margin: 0px;
}
#app {
  height: 100%;
}
</style>
