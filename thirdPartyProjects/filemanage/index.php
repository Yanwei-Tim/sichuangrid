<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
 * CKFinder
 * ========
 * http://ckfinder.com
 * Copyright (C) 2007-2010, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>共享文件夹</title>
	<meta name="robots" content="noindex, nofollow" />
	<link href="sample.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="ckfinder.js"></script>
	<style type="text/css">
		body, html, iframe, #ckfinder {
			margin: 0;
			padding: 0;
			border: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
		}
	</style>
</head>
<body>

<?php

require_once 'ckfinder.php' ;

$finder = new CKFinder() ;
$finder->BasePath = '/filemanage/' ;    
$finder->Create() ;


?>
</body>
</html>
