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
  Option,
  Breadcrumb,
  BreadcrumbItem,
  Row,
  Table,
  TableColumn,
  Pagination,
  Tag,
  Popover,
  Checkbox,
  CheckboxGroup
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
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Row)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Pagination)
Vue.use(Tag)
Vue.use(Popover)
Vue.use(Checkbox)
Vue.use(CheckboxGroup)

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
