import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/store';
import { Menu, MenuItem, Tooltip, Input, Button } from 'element-ui';

Vue.config.productionTip = false

Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Tooltip)
Vue.use(Input)
Vue.use(Button)

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
