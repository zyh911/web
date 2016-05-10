#!/usr/bin/perl -w

## prints out all the values of an HTML form

use CGI ":standard";

sub error {
    print "Error file could not be opened in conelec1.pl <br/>";
    print end_html();
    exit(1);
}

my($FirstName, $LastName, $Age, $Sex, $ID_Number, $watch, $holiday, $knowledge, $chat) = 
(param("Personal_FirstName"), 
param("Personal_LastName"), 
param("Personal_Age"), 
param("Personal_Sex"), 
param("Personal_IDNumber"), 
param("watch"),
param("holiday"),  
param("knowledge"),  
param("chat"));

print header();
print "<!DOCTYPE>\n<html>\n<head>\n<title>Dump</title>\n</head>\n";
print "<body>\n<h2>Here is the reault:</h2>\n<ol>\n";
print "<li><p>FirstName: $FirstName</p></li>\n";
print "<li><p>LastName: $LastName</p></li>\n";
print "<li><p>Age: $Age</p></li>\n";
print "<li><p>Sex: $Sex</p></li>\n";
print "<li><p>ID Number: $ID_Number</p></li>\n";
print "<li><p>Choose one(Dunno is right): $watch</p></li>\n";
print "<li><p>Where want to go: $holiday</p></li>\n";
print "<li><p>How well do you learn JavaScript?(5 for completely understanding, and 1 for knowing nothing): $knowledge</p></li>\n";
print "<li><p>Else: $chat</p></li>\n<ol>\n";
print "</body>\n</html>\n";
