<template>
  <div id="app">
    <div id="scene-container" ref="sceneContainer">temp</div>
    <!-- <viewport /> -->
  </div>
</template>

<script>
// import ViewPort from '@/components/common/ViewPort.vue';
import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import Stats from 'stats.js';
import * as dat from 'dat.gui';

export default {
  name: 'Temp',
  data() {
    return {
      container: null,
      scene: null,
      camera: null,
      controls: null,
      renderer: null,
      stats: null,
    };
  },
  components: {
    // viewport: ViewPort,
  },
  methods: {
    init() {
      // Loader
      const gltfLoader = new GLTFLoader();
      gltfLoader.load(
        '/three-assets/totoro.glb',
        (gltf) => {
          const root = gltf.scene;
          console.log(root);
          this.scene.add(root);

          root.traverse((obj) => {
            if (obj.castShadow !== undefined) {
              obj.castShadow = true;
              obj.receiveShadow = true;
            }
          });
        },
        undefined,
        undefined
      );

      const fbxLoader = new FBXLoader();
      fbxLoader.load('/three-assets/casa.fbx', (fbx) => {
        const fbxObject = fbx;
        console.log(fbxObject, 'fbx');
        this.scene.add(fbxObject);
      });

      // debug
      const gui = new dat.GUI();

      // Sizes
      // const sizes = {
      //   width: window.innerWidth,
      //   height: window.innerHeight,
      // };

      // set container
      this.container = this.$refs.sceneContainer;
      // add stats
      this.stats = new Stats();
      this.container.appendChild(this.stats.dom);

      // add camera
      const fov = 60; // Field of view
      const aspect = this.container.clientWidth / this.container.clientHeight;
      console.log(aspect, 'aspect');
      const near = 0.001; // the near clipping plane
      const far = 1000000; // the far clipping plane

      const camera = new THREE.PerspectiveCamera(fov, aspect, near, far);
      camera.position.set(0, 10, 20);

      this.camera = camera;
      // Base camera

      // create scene
      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color('skyblue');
      // add lights
      const ambientLight = new THREE.HemisphereLight(
        0xffffff, // bright sky color
        0x222222, // dim ground color
        1 // intensity
      );
      const mainLight = new THREE.DirectionalLight(0xffffff, 4.0);
      mainLight.castShadow = true;
      mainLight.position.set(-250, 800, -850);
      mainLight.target.position.set(-550, 40, -450);

      mainLight.shadow.bias = -0.004;
      mainLight.shadow.mapSize.width = 2048;
      mainLight.shadow.mapSize.height = 2048;

      this.scene.add(ambientLight, mainLight);
      this.scene.add(mainLight.target);

      const cam = mainLight.shadow.camera;
      cam.near = 1;
      cam.far = 2000;
      cam.left = -1500;
      cam.right = 1500;
      cam.top = 1500;
      cam.bottom = -1500;

      this.scene.add(this.camera);
      gui
        .add(camera.position, 'x')
        .min(-3)
        .max(3)
        .step(0.01);
      gui
        .add(camera.position, 'y')
        .min(10)
        .max(50)
        .step(0.01);
      gui
        .add(camera.position, 'z')
        .min(-3)
        .max(3)
        .step(0.01);

      // add controls
      this.controls = new OrbitControls(this.camera, this.container);

      // create renderer
      this.renderer = new THREE.WebGLRenderer({ antialias: true });
      this.renderer.setSize(this.container.clientWidth, this.container.clientHeight);
      this.renderer.setPixelRatio(window.devicePixelRatio);
      this.renderer.gammaFactor = 2.2;
      this.renderer.outputEncoding = THREE.sRGBEncoding;
      this.renderer.physicallyCorrectLights = true;
      this.container.appendChild(this.renderer.domElement);
      this.renderer.shadowMap.enabled = true;
      // set aspect ratio to match the new browser window aspect ratio
      this.camera.aspect = this.container.clientWidth / this.container.clientHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(this.container.clientWidth, this.container.clientHeight);

      this.renderer.setAnimationLoop(() => {
        this.render();
      });
    },
    render() {
      this.renderer.render(this.scene, this.camera);
      this.stats.update();
    },
  },
  mounted() {
    this.init();
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
