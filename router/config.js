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
          name: '订单模块',
          meta: {
            icon: 'profile',
          },
          component: PageView,
          children: [
            {
              path: 'addorders',
              name: '订单添加',
              component: () => import('@/pages/orders/addOrders/AddOrders'),
            },
            {
              path: 'listorders',
              name: '订单浏览',
              component: () => import('@/pages/orders/listOrders/ListOrders'),
            },
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
      ]
    },
  ]
}

export default options