<template>
  <div id="app">
    <div id="scene-container" ref="sceneContainer" @click="onDocumentMouseDown">temp</div>
  </div>
</template>

<script>
// import ViewPort from '@/components/common/ViewPort.vue';
import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';
// import { FBXLoader } from 'three/examples/jsm/loaders/FBXLoader';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls';
import Stats from 'stats.js';
import * as dat from 'dat.gui';

var mouse = new THREE.Vector2();
// SELECTED;
// var radius = 100,
//   theta = 0;

export default {
  name: 'Bamboo',
  data() {
    return {
      container: null,
      scene: null,
      camera: null,
      controls: null,
      renderer: null,
      stats: null,
      raycaster: null,
      totoro: null,
    };
  },
  components: {
    // viewport: ViewPort,
  },
  methods: {
    init() {
      // create scene
      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color('skyblue');

      // Loader
      const gltfLoader = new GLTFLoader();
      gltfLoader.load(
        '/three-assets/totoro.glb',
        (gltf) => {
          const root = gltf.scene;
          root.position.set(170, -150, -90);

          this.totoro = root;
          console.log(root.position, 'totoro');
          this.scene.add(root);

          // root position
          // root.position.x = mouse;
          // root.position.z = mouse;
          gui
            .add(root.position, 'x')
            .min(-1000)
            .max(1000)
            .step(0.01);
          gui
            .add(root.position, 'y')
            .min(-1000)
            .max(1000)
            .step(0.01);
          gui
            .add(root.position, 'z')
            .min(-1000)
            .max(1000)
            .step(0.01);
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

      const glbLoader = new GLTFLoader();
      glbLoader.load(
        '/three-assets/town.glb',
        (glb) => {
          const glbObject = glb.scene;
          console.log(glbObject, 'glb');
          this.scene.add(glbObject);
        },
        undefined,
        undefined
      );

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
      const near = 1; // the near clipping plane
      const far = 10000; // the far clipping plane

      this.camera = new THREE.PerspectiveCamera(fov, aspect, near, far);
      this.camera.position.set(330, 50, 100);
      // gui
      //   .add(camera.position, 'x')
      //   .min(-1000)
      //   .max(1000)
      //   .step(0.1);
      // gui
      //   .add(camera.position, 'y')
      //   .min(-1000)
      //   .max(1000)
      //   .step(0.1);
      // gui
      //   .add(camera.position, 'z')
      //   .min(-1000)
      //   .max(1000)
      //   .step(0.1);
      // this.camera = camera;

      // Base camera

      // add lights
      const ambient = new THREE.AmbientLight(0x404040);
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

      // gui
      //   .add(camera.position, 'x')
      //   .min(-3)
      //   .max(3)
      //   .step(0.01);
      // gui
      //   .add(camera.position, 'y')
      //   .min(10)
      //   .max(50)
      //   .step(0.01);
      // gui
      //   .add(camera.position, 'z')
      //   .min(-3)
      //   .max(3)
      //   .step(0.01);

      // Geometry
      // var geometry = new THREE.BoxBufferGeometry(20, 20, 20);

      // add controls
      this.controls = new OrbitControls(this.camera, this.container);
      // this.controls.maxPolarAngle = Math.PI * 0.495;
      // this.controls.target.set(0, 10, 0);
      // this.controls.minDistance = 40.0;
      // this.controls.maxDistance = 200.0;
      this.controls.update();

      // create renderer
      this.renderer = new THREE.WebGLRenderer({ antialias: true });
      this.renderer.setSize(this.container.clientWidth, this.container.clientHeight);
      this.renderer.setPixelRatio(window.devicePixelRatio);
      this.renderer.gammaFactor = 2.2;
      this.renderer.outputEncoding = THREE.sRGBEncoding;
      this.renderer.physicallyCorrectLights = true;
      this.container.appendChild(this.renderer.domElement);
      this.renderer.shadowMap.enabled = true;

      // Raycaster
      this.raycaster = new THREE.Raycaster();
      this.container.addEventListener('mousemove', this.onDocumentMouseMove, false);
      this.container.addEventListener('click', this.onDocumentMouseDown, false);
      this.container.addEventListener('touchstart', this.onDocumentTouch, false);
      window.addEventListener('resize', this.onWindowResize, false);

      // set aspect ratio to match the new browser window aspect ratio
      this.camera.aspect = this.container.clientWidth / this.container.clientHeight;
      this.camera.updateProjectionMatrix();

      this.renderer.setSize(this.container.clientWidth, this.container.clientHeight);
      this.renderer.setAnimationLoop(() => {
        this.render();
      });
    },
    onWindowResize() {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(window.innerWidth * 0.375, window.innerHeight * 0.375);
      // console.log('resized');
    },
    onDocumentMouseMove(event) {
      event.preventDefault();
      var gap1 = event.clientY - event.offsetY;
      var gap2 = event.clientX - event.offsetX;
      mouse.x = ((event.clientX - gap2) / (window.innerWidth * 0.375)) * 2 - 1;
      mouse.y = ((event.clienty - gap1) / (window.innerhEIGHT * 0.375)) * 2 + 1;
      this.totoro.position.x = mouse.x;
      this.totoro.position.y = mouse.y;
      this.totoro.position.z = mouse.z;
      console.log('마우스무브???', this.totoro.position.x, this.totoro.position.y, this.totoro.position.z);
    },
    onDocumentMouseDown(event) {
      event.preventDefault();
      var gap1 = event.clientY - event.offsetY;
      var gap2 = event.clientX - event.offsetX;
      mouse.x = ((event.clientX - gap2) / (window.innerWidth * 0.375)) * 2 - 1;
      mouse.y = ((event.clienty - gap1) / (window.innerhEIGHT * 0.375)) * 2 + 1;
      this.totoro.position.x = mouse.x;
      this.totoro.position.y = mouse.y;
      this.totoro.position.z = mouse.z;
      console.log('클릭???', this.totoro.position.x, this.totoro.position.y, this.totoro.position.z);
    },
    onDocumentTouch(event) {
      event.preventDefault();
      console.log(event.changedTouches);
      const _event = event.changedTouches[0];
      console.log(_event);
      var gap_x = _event.screenX - _event.clientX;
      var gap_y = _event.screenY - _event.clientY;
      console.log('gap', gap_x, gap_y);
      console.log('tx,ty', tx, ty);

      var tx = -1 + (_event.clientX / window.innerWidth) * 2;
      var ty = 1 - (_event.clientY / window.innerHeight) * 2;

      // this.totoro.position.x = mouse.x;
      this.totoro.position.y = ty;
      this.totoro.position.z = tx;
      console.log('터치???', this.totoro.position.x, this.totoro.position.y, this.totoro.position.z);
    },
    animate() {
      requestAnimationFrame(this.animate);
      this.controls.update();
      this.render();
    },
    render() {
      // Find intersections
      // this.raycaster.setFromCamera(mouse, this.camera);
      // var intersects = this.raycaster.intersectObjects(this.scene.children);
      // if (intersects.length > 0) {
      //   if (SELECTED != intersects[0].object) {
      //     if (SELECTED) SELECTED.material.emissive.setHex(SELECTED.currentHex);
      //     SELECTED = intersects[0].object;
      //     SELECTED.material.emissive.setHex(0xff0000);
      //     this.container.style.cursor = 'pointer';
      //   } else {
      //     if (SELECTED) {
      //       SELECTED.material.emissive.setHex(SELECTED.currentHex);
      //       SELECTED = null;
      //       this.container.style.cursor = 'auto';
      //     }
      //   }
      // }
      this.renderer.render(this.scene, this.camera);
      this.stats.update();
    },
  },
  mounted() {
    this.init();
    this.animate();
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
