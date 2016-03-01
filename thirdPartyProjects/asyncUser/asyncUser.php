<?php
function createStaticVariable(){
	define('ACCESSKEY','f1b09eea8d7e69f7fae3d14f37ac82f1');
	define('ADD_MODEL','add');
	define('EDIT_MODEL','edit');
}

function getUserFromParams(){
	$user = new User();
	$user->userName=$_GET["userName"];
	$user->password=$_GET["password"];
	return $user;
}

require_once("user.php");
require_once('config/sqlConfig.php');

$user = getUserFromParams();
createStaticVariable();

if(constant("ACCESSKEY")!=$_GET["accessKey"]
	||$user->userName==null
	||$user->password==null){
	print("{success:false,errorMsg:'params error'}");
	return ;
}

$conn=new mysqli($dbhost,$dbuser,$dbpw,$dbname);
if($user->isExist($conn)){
	$user->updatePassword($conn);
	print("{success:true,msg:'update ".$user->userName." success'}");
}else{
	$user->addUser($conn);
	print("{success:true,msg:'add ".$user->userName." success'}");
}	
$conn->close();

?>
