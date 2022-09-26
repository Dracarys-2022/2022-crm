<template>
    <div>
        <div>请选择你的爱好(vue)</div>
        <input type="checkbox" v-model="hobby2" :value=hhh>{{hhh}}
		<input type="checkbox" v-model="hobby2" value="游泳">游泳
		<input type="checkbox" v-model="hobby2" value="健身">健身
		<input type="checkbox" v-model="hobby2" value="旅游">旅游
		<div>你已选中：{{hobby2}}</div>
    </div>
</template>
<script>
    export default({
        data(){
            return{
                hobby2:["游泳","旅游"],
                hhh:'你好'
            }
        },created(){
				console.log('created')
		},
    })
</script>