import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/store';
import {
  Menu,
  Submenu,
  MenuItem,
  Tooltip,
  Input,
  Button,
  Avatar,
  Collapse,
  CollapseItem,
  Dropdown,
  DropdownMenu,
  DropdownItem,
  Dialog,
  Form,
  FormItem,
  Select,
  Option
} from 'element-ui';

Vue.config.productionTip = false

Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Tooltip)
Vue.use(Input)
Vue.use(Button)
Vue.use(Avatar)
Vue.use(Collapse)
Vue.use(CollapseItem)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Dialog)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Select)
Vue.use(Option)

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
