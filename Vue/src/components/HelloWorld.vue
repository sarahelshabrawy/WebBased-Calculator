<template>
<div class = "calculator">
  <div id="display">{{expression}}</div>
  <div class="numbersGrid">
    <button id = '%' v-on:click= "doubleOperator($event)" class = "sign">%</button>
    <button v-on:click="clearAll" class = "sign">CE</button>
    <button v-on:click="clearAll" class = "sign">C</button>
    <button class = "sign" v-on:click="erase">⌫</button>
    <button id = '^ -1' v-on:click="uniOperator($event)" class = "sign">1/x</button>
    <button id = '^ 2' v-on:click= "uniOperator($event)" class = "sign">x^2</button>
    <button id = '^ 0.5' v-on:click= "uniOperator($event)" class = "sign">√</button>
    <button id = '÷' v-on:click= "doubleOperator($event)" class = "sign">÷</button>
    <button id = '7' v-on:click= "displayChar($event)" class = "number">7</button>
    <button id = '8' v-on:click= "displayChar($event)" class = "number">8</button>
    <button id = '9' v-on:click= "displayChar($event)" class = "number">9</button>
    <button id = 'x' v-on:click= "doubleOperator($event)" class = "sign">x</button>
    <button id = '4' v-on:click= "displayChar($event)" class = "number">4</button>
    <button id = '5' v-on:click= "displayChar($event)" class = "number">5</button>
    <button id = '6' v-on:click= "displayChar($event)" class = "number">6</button>
    <button id = '-' v-on:click= "doubleOperator($event)" class = "sign">-</button>
    <button id = '1' v-on:click= "displayChar($event)" class = "number">1</button>
    <button id = '2' v-on:click= "displayChar($event)" class = "number">2</button>
    <button id = '3' v-on:click= "displayChar($event)" class = "number">3</button>
    <button id = '+' v-on:click= "doubleOperator($event)" class = "sign">+</button>
    <button id="x -1" v-on:click="uniOperator($event)" class = "sign">±</button>
    <button id = '0' v-on:click= "displayChar($event)" class = "number">0</button>
    <button id = '.' v-on:click= "displayChar($event)" class = "sign">.</button>
    <button v-on:click="equal" class = "sign">=</button>
</div>
  


  </div>
</template>

<script>
import axios from 'axios'

const apiUrl = "http://localhost:8080/calculate?expression="
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },data(){
    return{
      signSet : false,
      resultDisplayed : true,
      expression : "0"
    }
  },
  methods: {
    displayChar:function (e){
      const number = e.currentTarget.id
      if(this.resultDisplayed === true && this.signSet === false ){
        if(number !== '.')
        this.expression = ""
        this.resultDisplayed = false
      }
      const expString = String(this.expression)
      //avoiding adding two decimal points
      if(number === '.' && (expString.charAt(expString.length-1)===' '||expString.indexOf('.')>=0))
        return
      this.expression = this.expression + number

    },
    equal:async function () {
      await this.fetchData()
      this.resultDisplayed = true
      this.signSet = false
    },
    uniOperator:async function (e) {
      const operator = e.currentTarget.id
      if (this.signSet) {
        this.expression = "E"
        this.signSet = false
      } else {
        this.expression = this.expression + " " + operator
        await this.fetchData()
      }
      this.resultDisplayed = true
    },
    doubleOperator:async function (e){
      const operator = e.currentTarget.id
      if(this.signSet)
        await this.fetchData()
      else
        this.signSet = true

      if(this.expression==="E") {
        this.resultDisplayed = true
        this.signSet = false
      }
      else
        this.expression = this.expression + " " + operator + " "
    },
    fetchData :async function () {
      console.log(apiUrl+encodeURIComponent(this.expression))
      const response = await axios.get(apiUrl+encodeURIComponent(this.expression))
      this.expression = response.data
    },
    erase:function(){
      let expString = String(this.expression);
      const lastChar = expString.charAt(expString.length-1);
      if(lastChar === ' ')
        expString = expString.substr(0,expString.length-3);
      else
        expString = expString.substr(0,expString.length-1);
      if(expString === "") {
        expString = "0"
        this.resultDisplayed = true
      }
      this.expression = expString
    },
    clearAll:function (){
      this.expression = "0"
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#display{
  padding-top: 60px;
  font-size: 40px;
  display: block;
  overflow: auto;
  width: 600px;
  text-align: right;
background: black;
  color: white;
}
.numbersGrid{
  display: grid;
  grid-template-columns: repeat(4, 150px);
}
.number{
  color: white;
    font-size: 40px;
  background: black;
  border: 1px #656366 solid;
}
.number:hover{
  background: #656366;
}
.sign{
    padding: 10px;
  background : black;
  font-size: 40px;
  color: #A76B09;
  border: 1px #656366 solid;

}
.sign:hover{
  background: darkgrey;
}
</style>
