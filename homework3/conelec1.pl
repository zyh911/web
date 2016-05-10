#!/usr/bin/perl -w
# conelec1.pl
# This CGI program processes survey
#  form and updates the file that stores the survey

use CGI ":standard";

# error Ð a function to produce an error message for the client
#         and exit in case of open errors

sub error {
    print "Error file could not be opened in conelec1.pl <br/>";
    print end_html();
    exit(1);
}

# Begin main program
# Get the form values

my($name, $age, $gender, $email) = (param("name"), param("age"), param("gender"),
        param("email"));
# Produce the header for the reply page - do it here so
#  error messages appear on the page

print header();

# Set names for file locking and unlocking

$LOCK = 2;
$UNLOCK = 8;

# Set $index to the line index of the current vote


# Open and lock the survey data file

open(SURVDAT, "<survdat.dat") or error();
flock(SURVDAT, $LOCK);

# Read the survey data file, unlock it, and close it

chomp($num = <SURVDAT>);
$num = $num + 1;
$num = $num - 1;
$count = 0;
for ($count = 0; $count < $num; $count++) {
   chomp($file_lines[$count] = <SURVDAT>);
}

flock(SURVDAT, $UNLOCK);
close(SURVDAT);

# Split the line into its parts, increment the chosen
# device, and put it back together again
$s1 = "name: ";
$s2 = ", age: ";
$s3 = ", gender: ";
$s4 = ", e-mail address: ";
$newline = "$s1$name$s2$age$s3$gender$s4$email";
$file_lines[$count] = $newline;

# Reopen the survey data file for writing and lock it

open(SURVDAT, ">survdat.dat") or error();
flock(SURVDAT, $LOCK);

# Write out the file data, unlock the file, and close it

$num = $num + 1;
$num1 = "$num\n";
print SURVDAT "$num1";
for ($count = 0; $count < $num; $count++) {
    $line = $file_lines[$count];
    print SURVDAT "$line\n";
}

flock(SURVDAT, $UNLOCK);
close(SURVDAT);

# Build the Web page to thank the survey participant
print start_html("Thankyou");
print "Thanks for participating in our survey <br/>\n";
print "<p><a href = \"/homework/homework3.html\">go to survey page</a></p>\n";
print end_html();