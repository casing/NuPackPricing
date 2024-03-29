Instructions
============

1. Please answer the following question using Ruby, JavaScript or Java without using any third party gems, plugins, or jar files with the exception of MiniTest/RSpec, Jasmine, or JUnit for tests.

2. Treat this problem as a library.  No UI or file/console input is expected or required.

3. Please submit via publicly accessible repo so that we can review your commits.


Pricing problem
===============

NuPack is responsible for taking existing products and repackaging them for sale at electronic stores like Future Shop and Best Buy. Companies will phone up NuPack, explain the process and NuPack needs to quickly give them an estimate of how much it will cost. Different markups to the job:

* Without exception, there is a flat markup on all jobs of 5%
* For each person that needs to work on the job, there is a markup of 1.2%

Markups are also added depending on the types of materials involved:

* If pharmaceuticals are involved, there is an immediate 7.5% markup
* For food, there is a 13% markup
* Electronics require a 2% markup
* Everything else, there is no markup

Another system calculates the base price depending on how many products need to be repackaged. As such, the markup calculator should accept the initial base price along with the different categories of markups and calculate a final cost for a project.

The flat markup is calculated first and then all other markups are calculated on top of the base price plus flat markup.

For example...

Input 1:
--------
$1299.99

3 people

food

Input 2:
--------
$5432.00

1 person

drugs

Input 3:
--------
$12456.95

4 people

books


Output 1: $1591.58

Output 2: $6199.81

Output 3: $13707.63
