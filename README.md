[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.prolobjectlink/prolobjectlink-jpi/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.github.prolobjectlink/prolobjectlink-jpi)
![Build Status](https://github.com/prolobjectlink/prolobjectlink-jpi/actions/workflows/maven.yml/badge.svg?event=push)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=prolobjectlink_prolobjectlink-jpi&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=prolobjectlink_prolobjectlink-jpi)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=prolobjectlink_prolobjectlink-jpi&metric=coverage)](https://sonarcloud.io/summary/new_code?id=prolobjectlink_prolobjectlink-jpi)

Java Prolog Interface (JPI) is an Application Provider Interface (API) for interaction between Java and Prolog programming languages. Is a bidirectional interface that communicate Java applications with Prolog program or database and Prolog procedures with Java class and methods.

JPI is an abstraction layer over concrete prolog drivers over Prolog Engines. This API define all mechanism to interact with any Prolog Engine and maintain the application independent to a specific underlying engine. JPI have several connectors to open source prolog engines like SWI, YAP, XSB native engines and tuProlog, jTrolog, jLog Java based prolog engines.

JPI study all related Java-Prolog integration libraries and take the betters features from each solution with the propose to achieve a common integration interface. The last feature allows switch the under laying Prolog Engine driver and the application code still be the same.

JPI run over any Java Virtual Machine that support Java SE 5 or above. The project was tested over HotSpot and Open J9 Virtual Machines over Operating Systems like Windows (7,8,10), Linux (Debian, Ubuntu) and Mac OS X. Can be deployed on Servlets Containers like Jetty, Tomcat or Glassfish Application Server. JPI can be include in any Java Project using the commonest Java Integration Development Enviroment (IDE) like Eclipse, Netbeans, IntellijIDEA and so on.

JPI is developed and maintained by Prolobjectlink Project an open source initiative for build logic based applications using Prolog like fundamental Logic Programming Language in the persistence layer and application programming.

# Getting Started


## Install

Java Prolog Interface API is distributed with implementation adapter and concrete prolog driver library until it is possible according to related libraries licenses. The distributions are named normally such that prolobjectlink-jpi-jpl7-swi7-x.y.z-dist.zip meaning that this distribution is a JPI implementation over JPL version 7 or above and SWI-Prolog version 7 or above. The x.y.z is the distribution version. The distribution can be downloaded in zip or tar.gz compresses format. To install you need perform the following steps:
•Install Java Runtime Environment (JRE) 1.8 or above.
•Install Native Prolog Engine compatible to Operating System and your architecture. If the Prolog Engine to use is Java-based this step is omitted.
•Configure System Path with Prolog Engine routes. If the Prolog Engine to use is Java-based this step is omitted.
•Download Java Prolog Interface compatible to related prolog engine and unzip the distribution over Operating File System.
•Configure System Path with JPI unzip folder route.
•Open a new System console and type pllink –i to see the product information. 
For the JPI beginners we recommended start with a Pure Java-Prolog Engine because have less configuration aspects and native engine are more difficult to link.

# Getting started Java to Prolog

After installation and architecture compression you can use the hello world sample for test the system integration. This hello world sample show how interacts with JPI from Java programming language with Abstracted Prolog Engine. For the first experience we suggesting use a Java-based Prolog engine like tuProlog because have less configuration aspects.

Create in your preferred development environment an empty project. Set in the project build path the JPI downloaded libraries located at lib folder. Create a Main Java class that look like below code:

       public class Main {
                public static void main(String[] args) {
                PrologProvider provider = Prolog.getProvider();
                PrologEngine engine = provider.newEngine();
                engine.asserta("sample('hello wolrd')");
                PrologQuery query=engine.query("sample(X)");
                System.out.println(query.one());
                }
        }

        
# Architecture

JPI use a layered architecture pattern where every layer represents a component. The multi-engine Java Prolog connectors provide different levels of abstraction to simplify the implementations of common inter-operability task JPC. Java Prolog Connectors architectures describe three fundamentals layers, High-level API layer, Engine Adapter layer and Concrete Engine layer. High-level API layer define all services to be used by the users in the Java Prolog Application that is the final architecture layer on the architecture stack. High-level API provide the common implementation of Engine Abstraction, Data Type and Inter-Language conversion. The adapter layer adapts before mentioned features to communicate with the concrete Engine Layer, being the last responsible of execute the request services.

All existing Java Prolog Connectors implementation only bring support for Native Prolog Engines that have JVM bindings driver. JPI project is more inclusive and find connect all Prolog Engines Categories, Native and Java Based implementations. Some particular Java Based implementations in the future can be implement in strike forward mode the JPI interface. This particulars implementations reduce the impedance mismatch by remove the adapter layer. Therefore, JPI reference implementations will be faster than other that use adapter layer.

In JPI architecture stack in the bottom layer we have the Operating System. The Operating System can be Windows, Linux or Mac OS. Over Operating System, we have the native implementation of JVM and Prolog Engines like SWI, SWI7 and others. Over JVM and Prolog Engines we have Java Based Prolog Engines implementations and JVM bindings driver that share the runtime environment with JVM and native Prolog Engines. Over Java Based Prolog Engines implementations and JVM bindings drivers we have the JPI correspondent adapters. The adapters artifacts are the JPI implementations for each Prolog Engines. Over each adapter we have the JPI application provider interface and at the top stack we the final user application. The user application only interacts with the JPI providing single sourcing and transparency.

# Prolog Provider

Prolog Provider is the mechanism to interact with all Prolog components. Provider classes implementations allow create Prolog Terms, Prolog Engine, Java Prolog Converter, Prolog Parsers and system logger. Using io.github.prolobjectlink.prolog.Prolog bootstrap class the Prolog Providers are created specifying the provider class in getProvider(Class<?>)  method. This is the workflow start for JPI. When the Prolog Provider is created the next workflow step is the Prolog Terms creation using Java primitive types or using string with Prolog syntax. Provider allow create/parsing all Prolog Terms (Atoms, Numbers, Variables and Compounds). After term creation/parsing the next step is create an engine instance with newEngine() method. Using previous term creation and engine instance Prolog Queries can be formulated. This is possible because the engine class have multiples queries creation methods like a query factory. After query creation the Query interface present many methods to retrieve the query results. The result methods are based on result quantities, result terms, result object types, etc… This is the final step in the workflow. In the table 10 is resumed all Prolog Provider Interface methods.

# Prolog Terms

All Java Prolog connector libraries provide data type abstraction. Prolog data type abstraction have like ancestor the Term class. Prolog term is coding like abstract class and other Prolog terms are derived classes. In PrologTerm is defined the common term operation for all term hierarchy (functor, arity, compare, unify, arguments). The derived classes implement the correct behavior for each before mentioned operations. All Prolog data types PrologAtom, PrologNumber, PrologList, PrologStructure and PrologVariable are derived from this class. All before mentioned classes extends from this class the commons responsibilities. PrologTerm extends from Comparable interface to compare the current term with another term based on Standard Order.

PrologAtom represent the Prolog atom data type. Prolog atoms are can be of two kinds simple or complex. Simple atoms are defined like a single alpha numeric word that begin like initial lower case character. The complex atom is defining like any character sequence that begin and end with simple quotes. The string passed to build a simple atom should be match with {a-z}{A-Za-z0-9_}* regular expression. If the string passed to build an atom don't match with the before mentioned regular expression the atom constructor can be capable of create a complex atom automatically. For complex atom the string value can have the quotes or just can be absent. The printed string representation of the complex atom implementation set the quotes if they are needed. 


    PrologTerm pam = provider.newAtom("pam");
	 PrologTerm bob = provider.newAtom("bob");

PrologDouble represent a double precision floating point number. Extends from PrologNumber who contains an immutable Double instance. The Prolog Provider is the mechanism to create a new Prolog double invoking PrologProvider.newDouble(Number). PrologFloat represent a single precision floating point number. Extends from PrologNumber who contains an immutable Float instance. The Prolog Provider is the mechanism to create a new Prolog float invoking PrologProvider.newFloat(Number). PrologInteger represent an integer number. Extends from PrologNumber who contains an immutable Integer instance. The Prolog Provider is the mechanism to create a new Prolog integer invoking PrologProvider.newInteger(Number). Prolog term that represent a long integer number. Extends from PrologNumber who contains an immutable Long instance. The Prolog Provider is the mechanism to create a new Prolog long integer invoking PrologProvider.newLong(Number).

	PrologTerm pi = provider.newDouble(Math.PI);
	PrologTerm euler = provider.newFloat(Math.E);
	PrologTerm i = provider.newInteger(10);
	PrologTerm l = provider.newLong(10);

PrologVariable is created using PrologProvider.newVariable(int) for anonymous variables and PrologProvider.newVariable(String, int) for named variables. The Prolog variables can be used and reused because they remain in java heap. You can instantiate a prolog variable and used it any times in the same clause because refer to same variable every time.  The integer parameter represents the declaration variable order in the Prolog clause starting with zero.

	 PrologTerm x = provider.newVariable("X", 0);
	 PrologTerm y = provider.newVariable("Y", 1);
	 PrologTerm z = provider.newVariable("Z", 2);
	 
	 engine.assertz(
	 	provider.newStructure(grandparent, x, z),
	 	provider.newStructure(parent, x, y),
	                provider.newStructure(parent, y, z)
			);

PrologReference term is inspired on JPL JRef. This term is like a structure compound term that have like argument the object identification atom. The functor is the @ character and the arity is 1. An example of this prolog term is e.g. @(J#00000000000000425). To access to the referenced object, is necessary use PrologTerm.getObject().

PrologList are a special compound term that have like functor a dot (.) and arity equals 2. Prolog list are recursively defined. The first item in the list is referred like list head and the second item list tail. The list tail can be another list that contains head and tail. A special list case is the empty list denoted by no items brackets ([]). The arity for this empty list is zero. The Prolog Provider is the mechanism to create a new PrologList is invoking PrologProvider.newList() for empty list or PrologProvider.newList(PrologTerm) for one item list or PrologProvider.newList(PrologTerm[]) for many items. 

	 PrologTerm empty = provider.newList();
	 PrologTerm one = provider.newInteger(1);
	 PrologTerm two = provider.newInteger(2);
	 PrologTerm three = provider.newInteger(3);
	 PrologTerm list = provider.newList(
	 			new PrologTerm[] { one, two, three}
	 		);		
	 for (PrologTerm prologTerm : list) {
	        System.out.println(prologTerm);
	 }

PrologList implement Iterable interface to be used in for each sentence iterating over every element present in the list.

	Iterator<PrologTerm> i = list.iterator();
	 while (i.hasNext()) {
	        PrologTerm prologTerm = i.next();
	        System.out.println(prologTerm);
	 }
	 
	 for (Iterator<PrologTerm> i = list.iterator(); i.hasNext();) {
	        PrologTerm prologTerm = i.next();
	        System.out.println(prologTerm);
	 }

Prolog structures consist in a relation the functor (structure name) and arguments enclosed between parenthesis. The Prolog Provider is the mechanism to create a new Prolog structures invoking PrologProvider.newStructure(String, PrologTerm...). Two structures are equals if and only if are structure and have equals functor and arguments. Structures terms unify only with same functor and arguments structures, with free variable or with with structures where your arguments unify if they have the same functor and arity. Structures have a special property named arity that means the number of arguments present in the structure. There are two special structures term. They are expressions (Two arguments structure term with operator functor) and atoms (functor with zero arguments). For the first special case must be used PrologProvider.newStructure(PrologTerm, String, PrologTerm) specifying operands like arguments and operator like functor.

	PrologTerm pam = provider.newAtom("pam");
	PrologTerm bob = provider.newAtom("bob");
	PrologTerm parent = provider.newStructure("parent", pam, bob);

# Prolog Engine

Prolog Engine provide a general propose application interface to interact with Prolog Programing Language. Is a convenient abstraction for interacting with Prolog Virtual Machine from Java. In Java Prolog Engine connectors libraries, the abstract engine is able to answer queries using the abstract term representation before mentioned. There are several implementation engines and in this project we try connect from top level engine to more concrete or specific Prolog Engine. Based on JPC we have a top level engine that communicate with more concretes engines. Over this concretes engines we offer several services to interact with the concrete engines with low coupling and platform independency.

# Prolog Query

Prolog query is the mechanism to query the prolog database loaded in prolog engine. The way to create a new prolog query is invoking query() method in the Prolog Engine. When this method is called the prolog query is open an only dispose() in PrologQuery object close the current query and release all internal resources. Prolog query have several methods to manipulate the result objects. The main difference is in return types and result quantities. The result types enough depending of desire data type.  Maps of variables name key and Prolog terms as value, Maps of variables name key and Java objects as value, List of before mentioned maps, Prolog terms array, Prolog terms matrix, list of Java Objects and list of list of Java Objects. Respect to result quantities Prolog query offer one, n-th or all possible solutions. This is an important feature because the Prolog engine is forced to retrieve the necessary solution quantities. Prolog query implement Iterable and Iterator. This implementation helps to obtain successive solutions present in the query.

	public class Main {
	public static void main(String[] args) {
		PrologProvider provider = Prolog.getProvider();
		PrologEngine engine = provider.newEngine("zoo.pl");
		 PrologVariable x = provider.newVariable("X", 0);
		 PrologQuery query = 			engine.query(provider.newStructure("dark", x));
		 while (query.hasNext()) {
		        PrologTerm value = 					query.nextVariablesSolution().get("X");
		        System.out.println(value);
		 }
		 query.dispose();
		 engine.dispose();
		}
	}
	
	public class Main {
		public static void main(String[] args) {
			PrologProvider provider = Prolog.getProvider();
			 PrologEngine engine = provider.newEngine("zoo.pl");
			 PrologVariable x = provider.newVariable("X", 0);
			 PrologQuery query = 				engine.query(provider.newStructure("dark", x));
			 for (Collection<PrologTerm> col : query) {
			        for (PrologTerm prologTerm : col) {
			                System.out.println(prologTerm);
			        }
			 }
			 query.dispose();
			 engine.dispose();
		}
	}
	
	public class Main {
		public static void main(String[] args) {
			PrologProvider provider = Prolog.getProvider();
			 PrologEngine engine = provider.newEngine("zoo.pl");
			 PrologVariable x = provider.newVariable("X", 0);
			 PrologQuery query = 				engine.query(provider.newStructure("dark", x));
			 List<Object> solution = query.oneResult();
			 for (int i = 0; i < solution.size(); i++) {
			        System.out.println(solution.get(i));
			 } 
			 query.dispose();
			 engine.dispose();
		}
	}

# Prolog Query Builder

Prolog query builder to create prolog queries. The mechanism to create a new query builder is using PrologEngine.newQueryBuilder(). The query builder emulates the query creation process. After define all participant terms with the begin(PrologTerm) method, we specify the first term in the query. If the query has more terms, they are created using comma(PrologTerm) for everyone. Clause builder have a getQueryString() for string representation of the clause in progress. After clause definition this builder have query() method that create the final query instance ready to be used.  The follow code show how create a Prolog query ?- big(X), dark(X). using PrologQueryBuilder interface.

	PrologVariable x = provider.newVariable("X", 0);
	PrologStructure big = provider.newStructure("big", x);
	PrologStructure dark = provider.newStructure("dark", x);
	PrologQueryBuilder builder = engine.newQueryBuilder();
	PrologQuery query = builder.begin(dark).comma(big).query();
	
# Prolog Clause

Prolog clause is composed by two prolog terms that define a prolog clause, the head and the body. This representation considers the prolog clause body like a single term. If the body is a conjunctive set of terms, the body is a structure with functor/arity (, /2) and the first argument is the first element in the conjunction and the rest is a recursive functor/arity (, /2). The functor and arity for the clause is given from head term functor and arity. This class define some properties for commons prolog clause implementations. They are boolean flags that indicate if the prolog clause is dynamic multi-file and discontiguos. This class have several methods to access to the clause components and retrieve some clause properties and information about it. Additionally, this class contains a prolog provider reference for build terms in some operations.

# Prolog Clause Builder

Prolog clause builder to create prolog clauses. The mechanism to create a new clause builder is using PrologEngine.newClauseBuilder(). The clause builder emulates the clause creation process. After define all participant terms with the begin(PrologTerm) method, we specify the head of the clause. If the clause is a rule, after head definition, the clause body is created with neck(PrologTerm) for the first term in the clause body. If the clause body have more terms, they are created using comma(PrologTerm) for everyone. Clause builder have a getClauseString() for string representation of the clause in progress. After clause definition this builder have asserta(), assertz(),clause(),retract() that use the wrapped engine invoking the correspondent methods for check, insert or remove clause respectively.

	PrologTerm z = provider.newVariable("Z", 0);
	PrologTerm darkZ = provider.newStructure("dark", z);
	PrologTerm blackZ = provider.newStructure("black", z);
	PrologTerm brownZ = provider.newStructure("brown", z);
	PrologClauseBuilder builder = engine.newClauseBuilder();
	builder.begin(darkZ).neck(blackZ).assertz();
	builder.begin(darkZ).neck(brownZ).assertz();

The Prolog result in database is showed in the follow code.  The table 19 show the Prolog clause builder interface methods.

	dark(Z): - 
	        black(Z).
	 dark(Z): - 
	        brown(Z).
	 
# Prolog Scripting in Java

Java 6 added scripting support to the Java platform that lets a Java application execute scripts written in scripting languages such as Rhino JavaScript, Groovy, Jython, JRuby, Nashorn JavaScript, etc. All classes and interfaces in the Java Scripting API are in the javax.script package. Using a scripting language in a Java application provides several advantages, dynamic type, simple way to write programs, user customization, easy way to develop and provide domain-specific features that are not available in Java. For achieve this propose Java Scripting API introduce a scripting engine component. A script engine is a software component that executes programs written in a particular scripting language. Typically, but not necessarily, a script engine is an implementation of an interpreter for a scripting language. To run a script in Java is necessary perform the following three steps, create a script engine manager, get an instance of a script engine from the script engine manager and Call the eval() method of the script engine to execute a script.
	
	public class Main {
		public static void main(String[] args) {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("prolog");
			Boolean result = engine.eval("?- X is 5+3.");
			Integer solution = engine.get("X");
			System.out.println(solution);
		}
	}

Using script engine, it possible read Prolog source file. Read Prolog source file allow coding all prolog source in separate mode respect to Java program.

	public class Main {
		public static void main(String[] args) {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("prolog");
			Boolean read = engine.eval(new FileReader("family.pl"));
			Boolean eval = engine.eval("?- parent( Parent, Child)");
			Object parent = engine.get("Parent");
			Object child = engine.get("Child");
			System.out.println(parent);
			System.out.println(child);
		}
	}
	 