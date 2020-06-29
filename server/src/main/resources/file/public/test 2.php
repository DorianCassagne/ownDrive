
<div>
  <ul> 
    <li>
      <a href="#">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span class="fa fa-facebook"></span>
      </a> 
    </li>
    <li>
      <a href="#">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span class="fa fa-twitter"></span>
      </a> 
    </li>
    <li>
      <a href="#">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span class="fa fa-instagram"></span>
      </a> 
    </li>
    <li>
      <a href="#">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span class="fa fa-linkedin"></span>
      </a> 
    </li>
  </ul>  
</div> 

<style>
					@import url('//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css');
* {
  padding: 0px;
  margin: 0px;
}
body{
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: #00071C;
}
 ul {
   position: relative;
   display: flex;
   transform: rotate(-25deg) skew(25deg);
   transform-style: preserve-3d;
}
ul li {
  position: relative;
  list-style: none;
  width: 60px;
  height: 60px;
  margin: 0px 20px;
}
ul li:before{
  content: '';
  position: absolute;
  bottom: -10px;
  left: -5px;
  width: 100%;
  height: 10px;
  background: #2a2a2a;
  trnasform-origin: top;
  transform: skewX(-41deg);
}
ul li:after{
  content: '';
  position: absolute;
  top:5px;
  left: -9px;
  width: 9px;
  height: 100%;
  background: #2a2a2a;
  trnasform-origin: right;
  transform: skewY(-49deg);
}
ul li span{
  position: absolute;
  top: 0;
  lef: 0;
  width: 100%;
  height: 100%;
  display: flex !important;
  background: #2a2a2a;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 30px !important;
  transition: 1.5s ease-out;
}
ul li:hover span {
  z-index: 1000;
  transition: .3s;
  color: #fff;
  box-shadw: -1px 1px 1px rgba(0, 0, 0, .5);
}
ul li:hover span:nth-child(5){
  transform: translate(40px, -40px);
  opacity: 1;
}
ul li:hover span:nth-child(4){
  transform: translate(30px, -30px);
  opacity: .8;
}
ul li:hover span:nth-child(3){
  transform: translate(20px, -20px);
  opacity: .6;
}
ul li:hover span:nth-child(2){
  transform: translate(10px, -10px);
  opacity: .4;
}ul li:hover span:nth-child(1){
  transform: translate(0px, 0px);
  opacity: .2;
}
ul li:nth-child(1):hover span{
  background: #52E19F !important;
}
ul li:nth-child(2):hover span{
  background: #2C3456 !important;
}
ul li:nth-child(3):hover span{
  background: #EA6E96 !important;
}
ul li:nth-child(4):hover span{
  background: #FCEB00 !important;
}		
							</style>
					