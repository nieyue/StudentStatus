import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

const router= new Router({
  // export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: '首页',
      redirect: "/login"
    },
 /*   {
      path: '/index',
      name: '首页',
      component: resolve=>require(['@/components/Index'],resolve)
    },*/
    {
      path: '/login',
      name: '登录',
      component: resolve=>require(['@/components/Login'],resolve)
    },
    
    {
      path: '/main',
      name: '主页',
      component: resolve=>require(['@/components/main/Main'],resolve),
      children:[
        {
          path: 'welcome/:pathParams',
          name: '欢迎页',
          component: resolve=>require(['@/components/main/config/Welcome'],resolve)
        },
        {
          path: 'account',
          name: '个人信息',
          component: resolve=>require(['@/components/main/account/SelfAccount'],resolve),
          children:[
            {
              path: 'selfAccount',
              name: '个人信息',
              component:  resolve=>require(['@/components/main/account/SelfAccount'],resolve)
          },]
        },
        {
          path: 'account',
          name: '管理员账户',
          component: resolve=>require(['@/components/main/account/ManagerAccount'],resolve),
          children:[
            {
              path: 'managerAccount',
              name: '管理员账户',
              component:  resolve=>require(['@/components/main/account/ManagerAccount'],resolve)
          },]
        },
        {
          path: 'account',
          name: '教师账户',
          component: resolve=>require(['@/components/main/account/TeacherAccount'],resolve),
          children:[
            {
              path: 'teacherAccount',
              name: '教师',
              component: resolve=>require(['@/components/main/account/TeacherAccount'],resolve),
            }
          ]
        },
        {
          path: 'account',
          name: '学生账户',
          component: resolve=>require(['@/components/main/account/StudentAccount'],resolve),
          children:[
            {
              path: 'studentAccount',
              name: '学生',
              component: resolve=>require(['@/components/main/account/StudentAccount'],resolve),
            }
          ]
        },
        /* {
          path: 'role/:pathParams',
          name: '角色',
          component: resolve=>require(['@/components/main/rolePermission/Role'],resolve)
        },
        {
          path: 'permission',
          name: '权限',
          component: resolve=>require(['@/components/main/rolePermission/Permission'],resolve)
        },
        {
          path: 'rolePermission/:roleId',
          name: '角色权限',
          component: resolve=>require(['@/components/main/rolePermission/RolePermission'],resolve)
        },
        {
          path: 'system/druid',
          name: '数据库监控',
          component: resolve=>require(['@/components/main/system/Druid'],resolve)
        }, */
        {
          path: 'system/swagger',
          name: 'API接口管理（swagger）',
          component: resolve=>require(['@/components/main/system/Swagger'],resolve)
        }
      ]
    }
  ]
})
/* router.beforeEach((to, from, next) => {
  if(to.fullPath.indexOf("role")>0
    ||to.fullPath.indexOf("permission")>0
    ||to.fullPath.indexOf("rolePermission")>0){
      //判断是否超级管理员，是就显示账户管理
      if(sessionStorage.getItem("account")){
        let account=JSON.parse(sessionStorage.getItem("account"));
        if(account.role.name=="超级管理员"){
        next()
      }else{
        next(false)
      }
    }else{
      next()
    }

  }else{
    next()
  }

}) */
export default router
