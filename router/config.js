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
          name: "库存模块",
          path: 'inventory',
          meta: {
            icon: 'bank',
          },
          component: PageView,
          children: [
            {
              path: 'stockin',
              name: '入库操作',
              component: () => import('@/pages/inventory/stockIn/StockIn'),
            },
            {
              path: 'liststock',
              name: '库存浏览',
              component: () => import('@/pages/inventory/stockIn/ListStock'),
            },
            {
              path: 'stockout',
              name: '出库操作',
              component: () => import('@/pages/inventory/stockOut/StockOut'),
            },
            {
              path: 'resultout',
              name: '出库结果',
              component: () => import('@/pages/inventory/stockOut/ResultOut'),
              meta: {
                invisible: true
              }
            },
            {
              path: 'updatestock',
              name: '库存信息修改',
              component: () => import('@/pages/inventory/stockIn/UpdateStock'),
              meta: {
                invisible: true
              }
            },
          ]
        },
        {
          path: 'petition',
          name: '投诉模块',
          meta: {
            icon: 'highlight',
          },
          component: PageView,
          children: [
            {
              path: 'addpetition',
              name: '投诉信息录入',
              component: () => import('@/pages/petition/addPetition/AddPetition'),
            },
            {
              path: 'listpetition',
              name: '投诉信息浏览',
              component: () => import('@/pages/petition/listPetition/ListPetition'),
            },
            {
              path: 'addoutcome',
              name: '记录投诉结果',
              component: () => import('@/pages/petition/listPetition/AddOutcome'),
              meta: {
                invisible: true
              }
            },
          ]
        },
        {
          path: 'orders',
          name: '订单管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: 'addOrders',
              name: '订单录入',
              component: () => import('@/pages/orders/addOrders')
            },
            {
              path: 'listOrders',
              name: '订单记录',
              component: () => import('@/pages/orders/listOrders')
            },
            {
              path: 'prefit',
              name: '利润管理',
              component: () => import('@/pages/orders/prefit')
            }
          ]
        },
        {
          path: 'visit',
          name: '拜访记录管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: 'addVisit',
              name: '拜访记录添加',
              component: () => import('@/pages/visit/addVisit')
            },
            {
              path: 'listVisit',
              name: '查看拜访记录',
              component: () => import('@/pages/visit/convi')
            }
          ]
        },{
          path: 'roles',
          name: '角色管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: 'addRole',
              name: '角色添加',
              component: () => import('@/pages/roles/addRole')
            },
            {
              path: 'listRole',
              name: '角色查看',
              component: () => import('@/pages/roles/listRole')
            }
          ]
        },{
          path: 'functions',
          name: '功能管理',
          meta: {
            icon: 'appstore-o'
          },
          component: PageView,
          children: [
            {
              path: 'addFunction',
              name: '功能添加',
              component: () => import('@/pages/functions/addFunction')
            },
            {
              path: 'listFunction',
              name: '功能查看',
              component: () => import('@/pages/functions/listFunction')
            }
            // ,{
            //   path: 'listcheck',
            //   name: '复选框测试',
            //   component: () => import('@/pages/functions/listcheck')
            // }
          ]
        },
        {
          path: 'product',
          name: '产品模块',
          meta: {
            icon: 'profile',
          },
          component: PageView,
          children: [
            {
              path: 'addproduct',
              name: '产品添加',
              component: () => import('@/pages/product/addProduct'),
            },
            {
              path: 'listproduct',
              name: '产品浏览',
              component: () => import('@/pages/product/ListProduct'),
            },
            {
              path: 'updateproduct',
              name: '产品更新',
              component: () => import('@/pages/product/UpdateProduct'),
            },
          ]
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