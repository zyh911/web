#!/usr/bin/perl -w
# conelec3.pl
# This CGI program processes survey
#  form and updates the file that stores the survey

use CGI ":standard";

# error Ð a function to produce an error message for the client
#         and exit in case of open errors

sub error {
    print "Error file could not be opened in conelec3.pl <br/>";
    print end_html();
    exit(1);
}
my(@aaa) = (param("checkbox"));
$dnum = @aaa;

$LOCK = 2;
$UNLOCK = 8;

#read

open(SURVDAT, "<survdat.dat") or error();
flock(SURVDAT, $LOCK);

chomp($num = <SURVDAT>);
$num = $num + 1;
$num = $num - 1;
$count = 0;
for ($count = 0; $count < $num; $count++) {
   chomp($file_lines[$count] = <SURVDAT>);
}

flock(SURVDAT, $UNLOCK);
close(SURVDAT);

#write

open(SURVDAT, ">survdat.dat") or error();
flock(SURVDAT, $LOCK);

$newnum = $num - $dnum;
$num1 = "$newnum\n";
print SURVDAT "$num1";
$zer = 0;
if($dnum eq $zer)
{
	for ($count = 0; $count < $num; $count ++) {
	    $line = $file_lines[$count];
	    print SURVDAT "$line\n";
	}
}
else
{
	$ii = 0;
	$ww = $aaa[$ii];
	$ww = $ww + 1;
	$ww = $ww - 1;
	for ($count = 0; $count < $num; $count ++) {
	    $line = $file_lines[$count];
		if($ww eq $count)
		{
			$ii ++;
			if($ii eq $dnum)
			{
				$ww = -1;
			}
			else
			{
				$ww = $aaa[$ii];
				$ww = $ww + 1;
				$ww = $ww - 1;
			}
		}
		else
		{
	    	print SURVDAT "$line\n";
	    }
	}
}

flock(SURVDAT, $UNLOCK);
close(SURVDAT);

print header();
print start_html("Successful Operation!");
print "<b>You have deleted them!</b><br />\n";
print "<body>\n<form action = \"conelec2.pl\" method = \"get\">\n";
print "<input type = \"submit\"  value = \"Query again!\" />\n</form>\n";
print "<p><a href = \"/homework/homework3.html\">go to survey page</a></p>\n";
print end_html();