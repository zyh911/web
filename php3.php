<!DOCTYPE>

<!-- php3 by ZhaoYuhang
     -->

<html>
<head>
<title> Deleted!! </title>
</head>
<body>
<h2>You have deleted what you wanted!!</h2>
<?php
error_reporting(15);

$db = mysql_connect("localhost", "usr_2015_36", "zyhyhzyy");
if(!$db)
{
	print "<h2>Error - Could not connect to MySQL</h2>";
	exit;
}
$er = mysql_select_db("db_2015_36");
if(!$er)
{
	print "<h2>Error - Could not select the guest database</h2>";
	exit;
}
$checkbox = $_POST['checkbox'];
$cnt = count($checkbox);

for($i = 0; $i < $cnt; $i ++)
{
	$j = $checkbox[$i];
	$query = "DELETE FROM guest WHERE Guest_ID = "."$j";
	$query = stripslashes($query);
	$result = mysql_query($query);
	if(!$result)
	{
		print "Error - the query could not be executed";
		$error = mysql_error();
		print "<p>" . $error . "</p>";
		exit;
	}
}
?>
<form 
    action = "php2.php" 
    method = "post">
<input type = "submit"  value = "Query again!" />
<p><a href = "homework4-1.html">go to survey page</a></p>
</body>
</html>