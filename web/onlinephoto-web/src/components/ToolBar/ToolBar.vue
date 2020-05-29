<template>
  <div class="toolbar">
    <div class="title">
       <span><h2>{{title}}</h2></span>
    </div>
    <slot></slot>
  </div>
</template>
<script>

export default {
    name:'toolbar',
    props: {
      title: String,
    },
   
}
</script>
<style>
  .toolbar {
    width: 100%;
    padding: 10px;
    background-color: #ffffff;
    overflow: hidden;
    line-height: 32px;
    border: 1px solid #e6ebf5;
    border-top: 2px solid #3c8dbc ;
    margin-bottom: 15px;
  }
  .title{
    color: #f10215;
    margin-bottom: 5px;
    font-weight: 100px;
  }
</style>
