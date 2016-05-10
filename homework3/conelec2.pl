#!/usr/bin/perl -w
# conelec2.pl - display the survey results

use CGI ":standard";

# error - a function to produce an error message for the client
#         and exit in case of open errors

sub error {
    print "Error - file could not be opened in conelec2.pl <br/>";
    print end_html();
    exit(1);
}

# Begin main program
# Initialize file locking/unlocking parameter

$LOCK = 2;
$UNLOCK = 8;

print header();
# Open, lock, read, and unlock the survey data file

open(SURVDAT, "<survdat.dat") or error();
flock(SURVDAT, $LOCK);
chomp($num = <SURVDAT>);
$num = $num + 1;
$num = $num - 1;
flock(SURVDAT, $UNLOCK);

# Create the beginning of the result Web page

print "<!DOCTYPE>\n<html>\n<head>\n<title>The Reasult of The Survey</title>\n</head>\n";
print "<body>\n<form action = \"conelec3.pl\" method = \"get\">\n";
print "<h2>the Reasult</h2>\n<br />\n";

# Split the input lines for females into age arrays

for($cnt = 0; $cnt < $num; $cnt ++)
{
	print "<input type = \"checkbox\" name = \"checkbox\" value = \"$cnt\" />";
	chomp($file_lines[$count] = <SURVDAT>);
	$line = $file_lines[$count];
	print "$line<br />\n";
}

print "<input type = \"submit\"  value = \"delete\" />\n</form>\n";
print "<p><a href = \"/homework/homework3.html\">go to survey page</a></p>\n";
print "</body>\n</html>\n";


