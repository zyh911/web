<!DOCTYPE>

<!-- php2 by ZhaoYuhang
     -->

<html>
<head>
<title> Query </title>
</head>
<body>
<form 
    action = "php3.php" 
    method = "post">
<table border = "1"><caption><h2> Query Results </h2></caption>
	<tr align = "center">
		<th>check what you want to delete</th>
		<th>Guest_ID</th>
		<th>Guest_Name</th>
		<th>Age</th>
		<th>Gender</th>
		<th>e-mail</th>
	</tr>
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
$query = "SELECT * FROM guest";
$query = stripslashes($query);
$result = mysql_query($query);
if(!$result)
{
	print "Error - the query could not be executed";
	$error = mysql_error();
	print "<p>" . $error . "</p>";
	exit;
}
$num_rows = mysql_num_rows($result);
$row = mysql_fetch_array($result);
$num_fields = sizeof($row);
for($row_num = 0; $row_num < $num_rows; $row_num ++)
{
	print "<tr align = \"center\">\n";
	print "<td><input type = \"checkbox\" name = \"checkbox[]\" value = \"".$row[0]."\" /></td>\n";
	for($field_num = 0; $field_num < $num_fields / 2; $field_num ++)
	{
		$str = htmlspecialchars($row[$field_num]);
		print "<td>".$str."</td>\n";
	}
	$row = mysql_fetch_array($result);
	print "</tr>\n";
}
?>
</table>
<input type = "submit"  value = "delete" />
</form>
<p><a href = "homework4-1.html">go to survey page</a></p>
</body>
</html>