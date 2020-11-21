import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App)
  ,data(){return{
      ans : 0}},

}).$mount('#app')
