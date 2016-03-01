<?php
class User{
	var $userName;
	var $password;
	
	function isExist($conn){
		$queryStmt =  $conn->stmt_init();
		$queryStmt->prepare("SELECT uid FROM pw_members WHERE username=? LIMIT 10");
		$queryStmt->bind_param("s", $preUserName);
		$preUserName=$this->userName;
		$queryStmt->execute();
		$queryStmt->store_result();
		$coutNum = $queryStmt->num_rows>0;
	    $queryStmt->close();
	    if($coutNum){
	    	return true;
	    }
	    return false;
	}
	
	function addUser($conn){
		$insert= "insert into pw_members (username,password,regdate) values (?,?,?)";
		$stmt = $conn->prepare($insert);
		$stmt->bind_param("sss",$preUserName,$prePassword,$regDate);
		$preUserName=$this->userName;
		$prePassword=$this->password;
		$regDate=time();
		$stmt->execute();
		$insertId = $stmt->insert_id;	
		$stmt->close();
		
		$insertMemberData= "insert into pw_memberdata(uid) values(?)";
		$stmtMemberData = $conn->prepare($insertMemberData);
		$stmtMemberData->bind_param("i",$uid);
		$uid=$insertId;
		$stmtMemberData->execute();
		$stmtMemberData->close();
		return "{success:true}";
	}
	function updatePassword($conn){
		$updateSql= "update pw_members set password=? where username=?";
		$stmt = $conn->prepare($updateSql);
		$stmt->bind_param("ss",$prePassword,$preUserName);
		$prePassword=$this->password;
		$preUserName=$this->userName;
		$updateNum = $stmt->execute();
		$stmt->close();
	}
}
?>