import TabsView from '@/layouts/tabs/TabsView'
import BlankView from '@/layouts/BlankView'
import PageView from '@/layouts/PageView'

// 路由配置
const options = {
  routes: [
    {
      path: '/login',
      name: '登录页',
      component: () => import('@/pages/login')
    },
    {
      path: '*',
      name: '404',
      component: () => import('@/pages/exception/404'),
    },
    {
      path: '/403',
      name: '403',
      component: () => import('@/pages/exception/403'),
    },
    
    {
      path: '/',
      name: '首页',
      component: TabsView,
      redirect: '/login',
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          meta: {
            icon: 'dashboard'
          },
          component: BlankView,
          children: [
            {
              path: 'workplace',
              name: '工作台',
              meta: {
                page: {
                  closable: false
                }
              },
              component: () => import('@/pages/dashboard/workplace'),
            },
            {
              path: 'analysis',
              name: '分析页',
              component: () => import('@/pages/dashboard/analysis'),
            }
          ]
        },
        {
          path: 'result',
          name: '结果页',
          meta: {
            icon: 'check-circle-o',
          },
          component: PageView,
          children: [
            {
              path: 'success',
              name: '成功',
              component: () => import('@/pages/result/Success')
            },
            {
              path: 'error',
              name: '失败',
              component: () => import('@/pages/result/Error')
            }
          ]
        },
        {
          path: 'exception',
          name: '异常页',
          meta: {
            icon: 'warning',
          },
          component: BlankView,
          children: [
            {
              path: '404',
              name: 'Exp404',
              component: () => import('@/pages/exception/404')
            },
            {
              path: '403',
              name: 'Exp403',
              component: () => import('@/pages/exception/403')
            },
            {
              path: '500',
              name: 'Exp500',
              component: () => import('@/pages/exception/500')
            }
          ]
        },
        {
          name: '带参菜单',
          path: 'router/query',
          meta: {
            icon: 'project',
            query: {
              name: '菜单默认参数'
            }
          },
          component: () => import('@/pages/Demo')
        },
        {
          name: '动态路由菜单',
          path: 'router/dynamic/:id',
          meta: {
            icon: 'project',
            params: {
              id: 123
            }
          },
          component: () => import('@/pages/Demo')
        },
        {
          path: 'client',
          name: '客户公司管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: '/client/clientAdd',
              name: '客户公司添加',
              component: () => import('@/pages/client/ClientAdd')
            },
            {
              path: '/client/clientList',
              name: '客户公司信息',
              component: () => import('@/pages/client/ClientList'),
             
            },
            {
              path: '/client/clientDetail',
              meta: {
                invisible:true,
              },
              name: '客户公司详情',
              component: () => import('@/pages/client/ClientDetail')
            },
            
          ]
        },
        {
          path: 'contact',
          name: '客户联系人管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: '/contact/contactAdd',
              name: '联系人添加',
              component: () => import('@/pages/contact/ContactAdd')
            },
            {
              path: '/contact/contactList',
              name: '联系人信息',
              component: () => import('@/pages/contact/ContactList')
            },
            {
              path: '/contact/contactDetail',
              meta: {
                invisible:true,
              },
              name: '客户联系人详情',
              component: () => import('@/pages/contact/ContactDetail')
            },
          ]
        },
        {
          path: 'operator',
          name: '用户管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: '/operator/operatorAdd',
              name: '用户添加',
              component: () => import('@/pages/operator/OperatorAdd')
            },
            {
              path: '/operator/operatorList',
              name: '用户信息',
              component: () => import('@/pages/operator/OperatorList')
            },
            {
              path: '/operator/operatorDetail',
              meta: {
                invisible:true,
              },
              name: '用户详情',
              component: () => import('@/pages/operator/OperatorDetail')
            },
          ]
        },
        {
          name: '个人中心',
          path: '/operator/center',
          meta: {
            invisible:true,
          },
          component: () => import('@/pages/operator/OperatorCenter')
        }
      ]
    },
  ]
}

export default options
