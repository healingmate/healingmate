<template>
  <div id="app">
    <div id="scene-container" ref="sceneContainer">bamboo</div>
  </div>
</template>

<script>
import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
// import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
// import { JoyStick } from './toon3d.js';
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
    };
  },
  components: {},
  methods: {
    init() {
      this.container = this.$refs.sceneContainer;
      this.clock = new THREE.Clock();
      // create scene
      this.scene = new THREE.Scene();
      // this.scene.background = new THREE.Color(0xa0a0a0);
      // this.scene.fog = new THREE.Fog(0xa0a0a0, 700, 4000);

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

      // Model

      const game = this;
      const loader = new GLTFLoader();

      loader.load(
        `${this.assetsPath}scene/DemoScene.gltf`,
        function(gltf) {
          console.log(gltf);
          // const object = gltf.scene;
          game.scene.add(gltf.scene);
          const tLoader = new THREE.TextureLoader();
          tLoader.load(`${game.assetsPath}scene/PolygonNature_01.jpg`, function(texture) {
            gltf.scene.traverse(function(child) {
              if (child.isMesh) {
                child.material.map = texture;
              }
            });
          });
        },
        undefined,
        undefined
      );

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
          this.onWindowResize;
        },
        false
      );
    },

    onWindowResize() {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(window.innerWidth, window.innerHeight);
    },

    animate() {
      const game = this;
      // const dt = this.clock.getDelta();
      requestAnimationFrame(function() {
        game.animate();
      });

      // if (this.player.mixer !== undefined) this.player.mixer.update(dt);
      // if (this.player.action == 'Walking') {
      //   const elapsedTime = Date.now() - this.player.actionTime;
      //   if (elapsedTime > 1000 && this.player.move.forward > 0) {
      //     this.action = 'Running';
      //   }
      // }

      // if (this.player.move !== undefined) {
      //   this.movePlayer(dt);
      //   this.player.object.rotateY(this.player.move.turn * dt);
      // }
      // if (this.player.cameras != undefined && this.player.cameras.active != undefined) {
      //   this.camera.position.lerp(this.player.cameras.active.getWorldPosition(new THREE.Vector3()), 0.05);
      //   const pos = this.player.object.position.clone();
      //   pos.y += 300;
      //   this.camera.lookAt(pos);
      // }

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
  mounted() {
    this.init();
    this.animate();
  },
  // computed: {
  //   action: {
  //     get: function() {
  //       return this.player.action;
  //     },
  //     set: function(name) {
  //       if (this.player.action == name) return;

  //       const _action = this.player.mixer.clipAction(this.animations[name]);
  //       _action.time = 0;

  //       this.player.mixer.stopAllAction();
  //       this.player.action = name;
  //       this.player.actionTime = Date.now();

  //       // _action.fadeIn(0.5);
  //       _action.play();
  //     },
  //   },
  // },
  created() {
    window.addEventListener('resize', this.onWindowResize);
    console.log('resized');
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
