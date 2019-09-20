Issues
------

See the issue tracker at https://github.com/prolobjectlink/${project.name} to create a new issue or take an existing one.

Changes and Build
------------------------

Fork the repository in GitHub.

Clone your froked repository in your prefered IDE

Prolobjectlink development requires.

- Java 1.8
- Maven 3.1.0 or avove

Make changes in your cloned repository

Run all test to see if the system still consistent after your changes

Create unit-tests and make sure that the include changes are covered to 100%

Run the benchmark to see if the system performance still consistent after your changes

Add a description of your changes in CHANGELOG.txt and src/changes/changes.xml

Commit the changes.

Run an integration test on Travis-CI

Submit a pull request.

New Implementations
-------------------

The project start with some adapters implementations over most used open source prolog engines.

We accept any new adapter implementation of another prolog engine not covered at this moment.

For this propose create a new GitHub source code repository naming this follow the project convension:

prolobjectlink-jpi-<new engine implementation name>

Create an new maven project in your prefered IDE named like repository.

Copy the src/assembly/dist.xml descriptor

Copy the src/build/filters forder and change by your console main entry point

Copy and clean src/changes/changes.xml to go reporting every change

Copy src/site folder to generate a similar project site.

Copy the pom.xml properties, build, report, etc... from another implementation

Change the project information.

Add your dependecies indluding Java Prolog Interface API

<dependencies>
	...
	<dependency>
		<groupId>org.prolobjectlink</groupId>
		<artifactId>prolobjectlink-jpi</artifactId>
		<version>[1.0-SNAPSHOT, )</version>
	</dependency>
	...
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>[4.10, )</version>
		<scope>test</scope>
	</dependency>
	...
</dependencies>

In test package copy the unit-tests cases from another implementation to develop in test driven mode.

We suggest like adapter implementation order begin with data types, parsers, engine and finally query.

Run all test to see if the system to see if your implementation pass all.

Create unit-tests and make sure that the include changes are covered to 100%

Create the benchmark to see the system performance.

Add a description of your changes in CHANGELOG.txt and src/changes/changes.xml

Commit the changes.

Run an integration test on Travis-CI or another CI system

Versioning
----------

Prolobjectlink version signature is Major.Minor.Micro.

Major version is change when the API compativility is broken.
Minor version is change when a new feature was include in the realease.
Micro version is change when some bug is fixed or some maintenance take place

Prolobjectlink suggest work over the started 1.Y.Z version to preserve compativility all the time.
You are free of make any change adding new features, fixing bugs or code maintenance.

Contact us
----------

Please contact us at owr project mailing list https://groups.google.com/group/prolobjectlink

Thanks for contributing to Prolobjectlink!