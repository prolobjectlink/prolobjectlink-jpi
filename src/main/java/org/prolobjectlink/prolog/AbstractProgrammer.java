/*-
 * #%L
 * prolobjectlink-jpi
 * %%
 * Copyright (C) 2012 - 2019 Prolobjectlink Project
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package org.prolobjectlink.prolog;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Partial implementation of {@link PrologProgrammer} interface.
 * 
 * @author Jose Zalacain
 * @since 1.0
 */
public abstract class AbstractProgrammer implements PrologProgrammer {

	protected final PrologProvider provider;
	private static final String NECK = " :- \n\t";
	private final Set<String> ignorePackages = new HashSet<String>();

	protected AbstractProgrammer(PrologProvider provider) {
		this.provider = provider;
		ignorePackages.add("sun/java2d/d3d");
		ignorePackages.add("com/sun/jmx/defaults");
		ignorePackages.add("com/sun/jmx/remote/protocol/iiop");
		ignorePackages.add("com/sun/jmx/snmp/daemon");
		ignorePackages.add("sun/util");
		ignorePackages.add("com/sun/istack/internal/localization");
		ignorePackages.add("sun/util/xml");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/sax");
		ignorePackages.add("sun/security/krb5/internal/rcache");
		ignorePackages.add("sun/security/krb5/internal/tools");
		ignorePackages.add("com/sun/corba/se/pept/transport");
		ignorePackages.add("com/sun/demo/jvmti/hprof");
		ignorePackages.add("sun/swing/text/html");
		ignorePackages.add("com/sun/xml/internal/stream/writers");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/packaging/mime/util");
		ignorePackages.add("com/sun/corba/se/impl/protocol/giopmsgheaders");
		ignorePackages.add("com/sun/corba/se/impl/resolver");
		ignorePackages.add("com/sun/jndi/ldap/sasl");
		ignorePackages.add("com/sun/security/auth/login");
		ignorePackages.add("com/sun/org/apache/bcel/internal");
		ignorePackages.add("com/sun/xml/internal/ws/api/handler");
		ignorePackages.add("sun/security/jgss");
		ignorePackages.add("sun/security/provider");
		ignorePackages.add("jdk/internal/org/xml/sax/helpers");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/writer/document/http");
		ignorePackages.add("com/sun/xml/internal/ws/spi/db");
		ignorePackages.add("com/sun/jndi/url/iiop");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/storage/implementations");
		ignorePackages.add("com/sun/org/glassfish/gmbal");
		ignorePackages.add("sun/tracing/dtrace");
		ignorePackages.add("com/sun/xml/internal/bind/v2/runtime/property");
		ignorePackages.add("com/sun/org/apache/xpath/internal/domapi");
		ignorePackages.add("sun/net/smtp");
		ignorePackages.add("com/sun/xml/internal/ws/api/ha");
		ignorePackages.add("com/sun/xml/internal/ws/client/sei");
		ignorePackages.add("com/sun/xml/internal/ws/db");
		ignorePackages.add("com/sun/org/glassfish/external/statistics/impl");
		ignorePackages.add("sun/management/resources");
		ignorePackages.add("sun/misc/resources");
		ignorePackages.add("com/sun/xml/internal/bind/v2/model/core");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/c14n/helper");
		ignorePackages.add("com/sun/management");
		ignorePackages.add("com/sun/corba/se/impl/orbutil/concurrent");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/stax/util");
		ignorePackages.add("com/sun/xml/internal/ws/api/pipe");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/util");
		ignorePackages.add("com/sun/org/apache/xalan/internal/res");
		ignorePackages.add("sun/net/ftp/impl");
		ignorePackages.add("com/sun/xml/internal/ws/spi");
		ignorePackages.add("com/sun/corba/se/pept/encoding");
		ignorePackages.add("com/sun/istack/internal/logging");
		ignorePackages.add("jdk/management/resource");
		ignorePackages.add("sun/applet");
		ignorePackages.add("com/sun/corba/se/impl/orbutil/closure");
		ignorePackages.add("com/sun/xml/internal/ws/server");
		ignorePackages.add("com/sun/org/apache/xml/internal/res");
		ignorePackages.add("com/sun/security/sasl/ntlm");
		ignorePackages.add("com/sun/xml/internal/ws/policy/jaxws");
		ignorePackages.add("sun/reflect/generics/repository");
		ignorePackages.add("com/sun/jndi/toolkit/dir");
		ignorePackages.add("com/sun/org/apache/xml/internal/resolver");
		ignorePackages.add("com/sun/rmi/rmid");
		ignorePackages.add("com/sun/imageio/spi");
		ignorePackages.add("sun/swing/plaf/windows");
		ignorePackages.add("com/sun/xml/internal/ws/protocol/xml");
		ignorePackages.add("sun/awt/image/codec");
		ignorePackages.add("sun/net/www/protocol/file");
		ignorePackages.add("sun/nio/ch/sctp");
		ignorePackages.add("sun/util/logging/resources");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/packaging/mime/internet");
		ignorePackages.add("com/sun/xml/internal/ws/util/exception");
		ignorePackages.add("sun/security/tools");
		ignorePackages.add("com/sun/xml/internal/bind/v2/runtime/reflect");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/c14n");
		ignorePackages.add("com/sun/beans/decoder");
		ignorePackages.add("com/sun/org/apache/xpath/internal");
		ignorePackages.add("com/sun/xml/internal/ws/client/dispatch");
		ignorePackages.add("com/sun/xml/internal/ws/policy/sourcemodel/wspolicy");
		ignorePackages.add("com/sun/corba/se/spi/transport");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xni");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/util");
		ignorePackages.add("com/sun/xml/internal/stream/buffer/sax");
		ignorePackages.add("sun/net/www/protocol/netdoc");
		ignorePackages.add("com/sun/corba/se/internal/iiop");
		ignorePackages.add("com/sun/corba/se/impl/javax/rmi/CORBA");
		ignorePackages.add("sun/security/rsa");
		ignorePackages.add("com/sun/corba/se/spi/logging");
		ignorePackages.add("com/sun/xml/internal/bind/v2/schemagen");
		ignorePackages.add("com/sun/xml/internal/bind/v2/bytecode");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xs/util");
		ignorePackages.add("com/sun/xml/internal/ws/config/management/policy");
		ignorePackages.add("com/sun/xml/internal/stream/util");
		ignorePackages.add("com/sun/xml/internal/ws/encoding/soap/streaming");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/writer/document/soap12");
		ignorePackages.add("sun/security/jca");
		ignorePackages.add("com/sun/corba/se/impl/activation");
		ignorePackages.add("com/sun/security/auth/module");
		ignorePackages.add("com/sun/org/apache/xalan/internal");
		ignorePackages.add("sun/security/validator");
		ignorePackages.add("com/sun/corba/se/impl/interceptors");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/transforms/params");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xs/opti");
		ignorePackages.add("com/sun/jmx/snmp/tasks");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xs");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/compiler/util");
		ignorePackages.add("com/sun/xml/internal/bind");
		ignorePackages.add("sun/awt/shell");
		ignorePackages.add("sun/net/www");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/storage");
		ignorePackages.add("com/sun/xml/internal/ws/util/xml");
		ignorePackages.add("com/sun/jndi/url/iiopname");
		ignorePackages.add("com/sun/xml/internal/bind/v2/model/util");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/signature");
		ignorePackages.add("sun/net/spi");
		ignorePackages.add("com/sun/imageio/plugins/wbmp");
		ignorePackages.add("com/sun/jndi/ldap");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xpointer");
		ignorePackages.add("sun/security/provider/certpath");
		ignorePackages.add("jdk/internal/org/xml/sax");
		ignorePackages.add("sun/net/www/protocol/jar");
		ignorePackages.add("sun/launcher/resources");
		ignorePackages.add("sun/management/snmp/jvminstr");
		ignorePackages.add("com/sun/imageio/stream");
		ignorePackages.add("jdk/internal/cmm");
		ignorePackages.add("sun/rmi/server");
		ignorePackages.add("com/sun/org/apache/xalan/internal/templates");
		ignorePackages.add("com/sun/swing/internal/plaf/metal/resources");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/runtime/output");
		ignorePackages.add("com/sun/xml/internal/ws/api/model/wsdl");
		ignorePackages.add("sun/text/resources/en");
		ignorePackages.add("com/sun/corba/se/impl/dynamicany");
		ignorePackages.add("jdk/internal/org/objectweb/asm/util");
		ignorePackages.add("com/sun/activation/registries");
		ignorePackages.add("com/sun/org/omg/CORBA/portable");
		ignorePackages.add("com/sun/beans");
		ignorePackages.add("com/sun/xml/internal/stream/dtd/nonvalidating");
		ignorePackages.add("com/sun/org/apache/xerces/internal/jaxp/validation");
		ignorePackages.add("com/sun/xml/internal/bind/api/impl");
		ignorePackages.add("com/sun/xml/internal/txw2");
		ignorePackages.add("sun/rmi/runtime");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/exceptions");
		ignorePackages.add("com/sun/xml/internal/ws/api/config/management/policy");
		ignorePackages.add("com/sun/corba/se/impl/orb");
		ignorePackages.add("com/sun/xml/internal/ws/transport/http");
		ignorePackages.add("sun/net/ftp");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xpath");
		ignorePackages.add("com/sun/xml/internal/ws/message/source");
		ignorePackages.add("com/sun/xml/internal/org/jvnet/mimepull");
		ignorePackages.add("sun/security/krb5/internal/crypto/dk");
		ignorePackages.add("com/sun/rowset");
		ignorePackages.add("com/sun/management/jmx");
		ignorePackages.add("com/sun/xml/internal/org/jvnet/fastinfoset/sax/helpers");
		ignorePackages.add("com/sun/xml/internal/org/jvnet/staxex");
		ignorePackages.add("sun/java2d/pipe/hw");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/client/p2p");
		ignorePackages.add("com/sun/corba/se/spi/orbutil/proxy");
		ignorePackages.add("sun/rmi/log");
		ignorePackages.add("sun/security/krb5/internal/util");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/compiler");
		ignorePackages.add("com/sun/jmx/snmp");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/soap/dynamic");
		ignorePackages.add("sun/net/www/protocol/https");
		ignorePackages.add("com/sun/security/sasl");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/writer/document");
		ignorePackages.add("com/sun/imageio/plugins/gif");
		ignorePackages.add("com/sun/org/glassfish/external/probe/provider");
		ignorePackages.add("com/sun/xml/internal/bind/v2/util");
		ignorePackages.add("com/sun/xml/internal/ws/api/fastinfoset");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/dtd");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/packaging/mime");
		ignorePackages.add("jdk/management/resource/internal/inst");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/dv");
		ignorePackages.add("com/sun/xml/internal/ws/encoding/soap");
		ignorePackages.add("sun/awt/dnd");
		ignorePackages.add("com/sun/xml/internal/stream/buffer");
		ignorePackages.add("com/sun/corba/se/internal/corba");
		ignorePackages.add("com/sun/image/codec/jpeg");
		ignorePackages.add("com/sun/corba/se/spi/extension");
		ignorePackages.add("com/sun/corba/se/impl/naming/pcosnaming");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/signature/reference");
		ignorePackages.add("sun/java2d/pipe");
		ignorePackages.add("com/sun/corba/se/impl/monitoring");
		ignorePackages.add("com/sun/xml/internal/bind/v2/schemagen/episode");
		ignorePackages.add("sun/io");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/writer/document/soap");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/stax/events");
		ignorePackages.add("com/sun/org/omg/SendingContext");
		ignorePackages.add("jdk/internal/org/objectweb/asm/tree");
		ignorePackages.add("sun/management/counter");
		ignorePackages.add("sun/security/pkcs10");
		ignorePackages.add("sun/nio/fs");
		ignorePackages.add("com/oracle/webservices/internal/api/message");
		ignorePackages.add("sun/security/smartcardio");
		ignorePackages.add("sun/net/dns");
		ignorePackages.add("sun/print/resources");
		ignorePackages.add("sun/util/calendar");
		ignorePackages.add("com/sun/corba/se/impl/encoding");
		ignorePackages.add("com/sun/xml/internal/ws/assembler/dev");
		ignorePackages.add("sun/security/pkcs12");
		ignorePackages.add("sun/reflect");
		ignorePackages.add("com/sun/istack/internal");
		ignorePackages.add("com/sun/xml/internal/bind/api");
		ignorePackages.add("sun/security/timestamp");
		ignorePackages.add("sun/awt/windows");
		ignorePackages.add("sun/security/provider/certpath/ldap");
		ignorePackages.add("com/sun/org/glassfish/external/statistics");
		ignorePackages.add("com/sun/jndi/toolkit/url");
		ignorePackages.add("sun/swing/plaf");
		ignorePackages.add("com/sun/org/apache/xerces/internal/utils");
		ignorePackages.add("com/sun/corba/se/spi/legacy/connection");
		ignorePackages.add("sun/security/krb5/internal/crypto");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/util/transform");
		ignorePackages.add("com/sun/xml/internal/ws/message");
		ignorePackages.add("com/sun/corba/se/spi/activation/InitialNameServicePackage");
		ignorePackages.add("sun/security/acl");
		ignorePackages.add("com/sun/jndi/ldap/ext");
		ignorePackages.add("sun/awt/util");
		ignorePackages.add("com/sun/xml/internal/ws/policy/privateutil");
		ignorePackages.add("sun/invoke/empty");
		ignorePackages.add("com/sun/jmx/snmp/IPAcl");
		ignorePackages.add("sun/text/normalizer");
		ignorePackages.add("com/sun/xml/internal/ws/message/saaj");
		ignorePackages.add("com/sun/xml/internal/ws/api/client");
		ignorePackages.add("com/sun/xml/internal/ws/config/metro/dev");
		ignorePackages.add("com/sun/beans/editors");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xni/grammars");
		ignorePackages.add("com/sun/corba/se/internal/CosNaming");
		ignorePackages.add("sun/net/www/protocol/http/logging");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/util");
		ignorePackages.add("sun/java2d/cmm");
		ignorePackages.add("com/sun/xml/internal/ws/addressing/model");
		ignorePackages.add("sun/management/snmp");
		ignorePackages.add("sun/dc/pr");
		ignorePackages.add("com/sun/xml/internal/ws/transport");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/c14n/implementations");
		ignorePackages.add("com/sun/xml/internal/bind/v2/runtime/reflect/opt");
		ignorePackages.add("com/sun/beans/infos");
		ignorePackages.add("com/sun/jndi/toolkit/ctx");
		ignorePackages.add("com/sun/jndi/url/ldap");
		ignorePackages.add("sun/awt");
		ignorePackages.add("sun/security/jgss/spi");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/transforms/implementations");
		ignorePackages.add("com/sun/xml/internal/ws/config/metro/util");
		ignorePackages.add("com/sun/org/apache/xml/internal/resolver/tools");
		ignorePackages.add("sun/net/www/content/image");
		ignorePackages.add("com/sun/org/apache/xpath/internal/axes");
		ignorePackages.add("sun/net/www/protocol/ftp");
		ignorePackages.add("com/sun/org/apache/xpath/internal/operations");
		ignorePackages.add("com/sun/corba/se/impl/oa/poa");
		ignorePackages.add("sun/awt/geom");
		ignorePackages.add("sun/launcher");
		ignorePackages.add("sun/nio/cs");
		ignorePackages.add("com/sun/jndi/cosnaming");
		ignorePackages.add("sun/net/spi/nameservice");
		ignorePackages.add("com/sun/xml/internal/stream");
		ignorePackages.add("sun/net/httpserver");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/org/apache/xerces/util");
		ignorePackages.add("sun/nio/ch");
		ignorePackages.add("com/sun/jndi/url/ldaps");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/alphabet");
		ignorePackages.add("sun/swing/plaf/synth");
		ignorePackages.add("com/sun/corba/se/impl/presentation/rmi");
		ignorePackages.add("sun/management/jmxremote");
		ignorePackages.add("sun/management/snmp/jvmmib");
		ignorePackages.add("sun/java2d/opengl");
		ignorePackages.add("com/sun/xml/internal/ws/api/model/wsdl/editable");
		ignorePackages.add("com/sun/xml/internal/ws/api/pipe/helper");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj");
		ignorePackages.add("com/sun/java/browser/net");
		ignorePackages.add("com/oracle/xmlns/internal/webservices/jaxws_databinding");
		ignorePackages.add("com/sun/org/apache/xerces/internal/parsers");
		ignorePackages.add("com/sun/xml/internal/ws/dump");
		ignorePackages.add("jdk/internal/util/xml/impl");
		ignorePackages.add("com/sun/xml/internal/ws/client");
		ignorePackages.add("sun/security/util");
		ignorePackages.add("com/sun/xml/internal/ws/api");
		ignorePackages.add("com/sun/xml/internal/bind/v2/model/nav");
		ignorePackages.add("com/sun/corba/se/spi/orbutil/fsm");
		ignorePackages.add("jdk/management/resource/internal");
		ignorePackages.add("com/sun/xml/internal/ws/api/policy/subject");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/algorithms");
		ignorePackages.add("com/sun/corba/se/impl/protocol");
		ignorePackages.add("com/sun/xml/internal/ws/addressing");
		ignorePackages.add("sun/dc");
		ignorePackages.add("sun/net/sdp");
		ignorePackages.add("com/sun/java/browser/dom");
		ignorePackages.add("com/sun/xml/internal/ws/api/wsdl/parser");
		ignorePackages.add("com/sun/xml/internal/ws/streaming");
		ignorePackages.add("com/sun/xml/internal/ws/api/policy");
		ignorePackages.add("com/oracle/webservices/internal/impl/encoding");
		ignorePackages.add("com/sun/xml/internal/ws/assembler/jaxws");
		ignorePackages.add("sun/security/pkcs");
		ignorePackages.add("sun/management/counter/perf");
		ignorePackages.add("com/sun/media/sound");
		ignorePackages.add("com/sun/org/omg/CORBA/ValueDefPackage");
		ignorePackages.add("sun/net/www/protocol/http/ntlm");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xs/traversers");
		ignorePackages.add("com/sun/xml/internal/ws/message/jaxb");
		ignorePackages.add("com/sun/org/apache/xerces/internal/dom/events");
		ignorePackages.add("sun/rmi/transport/tcp");
		ignorePackages.add("com/sun/xml/internal/bind/marshaller");
		ignorePackages.add("com/sun/corba/se/spi/orbutil/threadpool");
		ignorePackages.add("com/sun/org/apache/xml/internal/resolver/helpers");
		ignorePackages.add("com/sun/jmx/interceptor");
		ignorePackages.add("sun/util/locale/provider");
		ignorePackages.add("com/sun/corba/se/impl/oa");
		ignorePackages.add("com/sun/corba/se/impl/ior/iiop");
		ignorePackages.add("com/sun/jmx/snmp/mpm");
		ignorePackages.add("sun/awt/image");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/cmdline/getopt");
		ignorePackages.add("sun/security/krb5");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/cmdline");
		ignorePackages.add("com/sun/xml/internal/ws/encoding");
		ignorePackages.add("sun/security/krb5/internal/ccache");
		ignorePackages.add("com/sun/xml/internal/ws/api/model/soap");
		ignorePackages.add("sun/management/snmp/util");
		ignorePackages.add("com/sun/corba/se/impl/copyobject");
		ignorePackages.add("com/sun/java/swing/plaf/motif/resources");
		ignorePackages.add("com/sun/org/apache/xpath/internal/functions");
		ignorePackages.add("com/sun/org/apache/xerces/internal/util");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/soap");
		ignorePackages.add("com/sun/xml/internal/ws/policy");
		ignorePackages.add("com/sun/xml/internal/ws/fault");
		ignorePackages.add("sun/net/www/protocol/http/spnego");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xinclude");
		ignorePackages.add("sun/util/resources");
		ignorePackages.add("com/sun/corba/se/spi/copyobject");
		ignorePackages.add("com/sun/org/apache/xml/internal/security");
		ignorePackages.add("com/sun/corba/se/impl/orbutil/graph");
		ignorePackages.add("com/sun/xml/internal/fastinfoset");
		ignorePackages.add("jdk/internal/org/objectweb/asm/signature");
		ignorePackages.add("jdk/internal/org/objectweb/asm");
		ignorePackages.add("com/sun/org/glassfish/gmbal/util");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/stax");
		ignorePackages.add("com/sun/xml/internal/bind/v2/runtime/output");
		ignorePackages.add("com/sun/xml/internal/ws/policy/jaxws/spi");
		ignorePackages.add("sun/java2d/loops");
		ignorePackages.add("com/sun/org/apache/bcel/internal/classfile");
		ignorePackages.add("com/sun/corba/se/impl/javax/rmi");
		ignorePackages.add("sun/security/jgss/wrapper");
		ignorePackages.add("com/sun/xml/internal/ws/db/glassfish");
		ignorePackages.add("com/sun/jndi/url/corbaname");
		ignorePackages.add("com/sun/org/apache/xalan/internal/lib");
		ignorePackages.add("com/sun/corba/se/spi/oa");
		ignorePackages.add("com/sun/corba/se/impl/corba");
		ignorePackages.add("com/sun/xml/internal/ws/policy/subject");
		ignorePackages.add("com/sun/xml/internal/ws/policy/spi");
		ignorePackages.add("com/sun/xml/internal/bind/v2/model/annotation");
		ignorePackages.add("com/sun/corba/se/impl/logging");
		ignorePackages.add("com/sun/xml/internal/ws/server/sei");
		ignorePackages.add("sun/reflect/generics/factory");
		ignorePackages.add("com/sun/org/apache/xpath/internal/res");
		ignorePackages.add("com/sun/org/apache/xml/internal/utils");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/dv/xs");
		ignorePackages.add("com/sun/org/apache/xpath/internal/compiler");
		ignorePackages.add("com/sun/xml/internal/org/jvnet/fastinfoset/sax");
		ignorePackages.add("com/sun/security/sasl/util");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/utils");
		ignorePackages.add("com/sun/jndi/dns");
		ignorePackages.add("com/sun/jmx/remote/util");
		ignorePackages.add("com/sun/org/glassfish/external/arc");
		ignorePackages.add("sun/java2d/cmm/kcms");
		ignorePackages.add("sun/security/tools/keytool");
		ignorePackages.add("com/sun/naming/internal");
		ignorePackages.add("sun/net/www/content/text");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/tools");
		ignorePackages.add("com/sun/java/util/jar/pack");
		ignorePackages.add("com/sun/corba/se/impl/orbutil/threadpool");
		ignorePackages.add("jdk/management/cmm");
		ignorePackages.add("jdk/internal/org/objectweb/asm/tree/analysis");
		ignorePackages.add("com/sun/corba/se/pept/broker");
		ignorePackages.add("sun/util/locale");
		ignorePackages.add("com/sun/corba/se/spi/activation/RepositoryPackage");
		ignorePackages.add("com/sun/xml/internal/bind/v2");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/vocab");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/writer");
		ignorePackages.add("com/sun/security/ntlm");
		ignorePackages.add("sun/net/util");
		ignorePackages.add("com/sun/xml/internal/ws/encoding/xml");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/dv/dtd");
		ignorePackages.add("sun/reflect/generics/scope");
		ignorePackages.add("sun/net");
		ignorePackages.add("com/sun/java/swing/plaf/nimbus");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/algorithms/implementations");
		ignorePackages.add("sun/rmi/transport/proxy");
		ignorePackages.add("sun/rmi/transport");
		ignorePackages.add("sun/text/resources");
		ignorePackages.add("sun/util/spi");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/content/x509");
		ignorePackages.add("sun/management");
		ignorePackages.add("com/sun/net/ssl/internal/www/protocol/https");
		ignorePackages.add("jdk");
		ignorePackages.add("com/sun/net/ssl");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xslt");
		ignorePackages.add("com/sun/beans/finder");
		ignorePackages.add("com/sun/security/auth");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/io");
		ignorePackages.add("com/sun/xml/internal/ws/transport/http/server");
		ignorePackages.add("com/oracle/nio");
		ignorePackages.add("com/sun/jmx/remote/protocol/rmi");
		ignorePackages.add("com/sun/xml/internal/bind/v2/schemagen/xmlschema");
		ignorePackages.add("com/sun/corba/se/impl/io");
		ignorePackages.add("com/sun/corba/se/impl/oa/toa");
		ignorePackages.add("com/sun/xml/internal/ws/encoding/fastinfoset");
		ignorePackages.add("sun/java2d");
		ignorePackages.add("com/sun/org/apache/xpath/internal/objects");
		ignorePackages.add("com/sun/xml/internal/ws/policy/sourcemodel/attach");
		ignorePackages.add("com/sun/java/swing/plaf/windows");
		ignorePackages.add("com/sun/xml/internal/ws/model");
		ignorePackages.add("com/sun/jmx/remote/internal");
		ignorePackages.add("com/sun/org/apache/regexp/internal");
		ignorePackages.add("com/sun/xml/internal/bind/v2/runtime/unmarshaller");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xs/identity");
		ignorePackages.add("com/sun/net/httpserver");
		ignorePackages.add("com/sun/org/apache/xml/internal/dtm");
		ignorePackages.add("com/sun/org/omg/CORBA");
		ignorePackages.add("sun/security/jgss/spnego");
		ignorePackages.add("com/sun/corba/se/internal/POA");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/runtime");
		ignorePackages.add("com/sun/imageio/plugins/png");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/algorithm");
		ignorePackages.add("com/sun/corba/se/impl/orbutil");
		ignorePackages.add("sun/net/www/protocol/mailto");
		ignorePackages.add("com/sun/security/sasl/gsskerb");
		ignorePackages.add("com/sun/tracing");
		ignorePackages.add("sun/java2d/windows");
		ignorePackages.add("com/sun/xml/internal/bind/v2/model/impl");
		ignorePackages.add("com/sun/corba/se/spi/ior");
		ignorePackages.add("com/sun/corba/se/spi/monitoring");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/soap/ver1_1");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/soap/ver1_2");
		ignorePackages.add("com/sun/org/apache/xml/internal/dtm/ref/dom2dtm");
		ignorePackages.add("com/sun/accessibility/internal/resources");
		ignorePackages.add("com/sun/xml/internal/ws/encoding/policy");
		ignorePackages.add("sun/security/jgss/krb5");
		ignorePackages.add("com/sun/swing/internal/plaf/synth/resources");
		ignorePackages.add("com/sun/xml/internal/txw2/annotation");
		ignorePackages.add("com/sun/xml/internal/ws/api/model");
		ignorePackages.add("com/sun/xml/internal/ws/api/wsdl/writer");
		ignorePackages.add("com/sun/corba/se/impl/naming/cosnaming");
		ignorePackages.add("com/sun/org/apache/xpath/internal/jaxp");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/utils/resolver");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xpath/regex");
		ignorePackages.add("com/sun/net/httpserver/spi");
		ignorePackages.add("sun/swing/table");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys");
		ignorePackages.add("com/sun/xml/internal/bind/util");
		ignorePackages.add("com/sun/xml/internal/ws/addressing/policy");
		ignorePackages.add("com/sun/xml/internal/ws/handler");
		ignorePackages.add("com/sun/xml/internal/ws/protocol/soap");
		ignorePackages.add("com/sun/org/apache/xml/internal/utils/res");
		ignorePackages.add("com/sun/xml/internal/ws/api/server");
		ignorePackages.add("com/sun/awt");
		ignorePackages.add("jdk/internal/org/objectweb/asm/commons");
		ignorePackages.add("com/sun/xml/internal/org/jvnet/fastinfoset");
		ignorePackages.add("sun/security/provider/certpath/ssl");
		ignorePackages.add("com/sun/corba/se/impl/legacy/connection");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/dom");
		ignorePackages.add("sun/java2d/cmm/lcms");
		ignorePackages.add("sun/text/bidi");
		ignorePackages.add("com/sun/xml/internal/ws/util/pipe");
		ignorePackages.add("com/sun/jmx/snmp/agent");
		ignorePackages.add("com/sun/org/apache/bcel/internal/util");
		ignorePackages.add("sun/nio");
		ignorePackages.add("com/sun/org/omg/SendingContext/CodeBasePackage");
		ignorePackages.add("com/sun/xml/internal/stream/dtd");
		ignorePackages.add("com/sun/org/apache/xpath/internal/patterns");
		ignorePackages.add("sun/usagetracker");
		ignorePackages.add("sun/text");
		ignorePackages.add("sun/awt/event");
		ignorePackages.add("com/sun/xml/internal/ws/model/wsdl");
		ignorePackages.add("com/oracle/util");
		ignorePackages.add("com/sun/java_cup/internal/runtime");
		ignorePackages.add("com/sun/jndi/url/rmi");
		ignorePackages.add("com/sun/org/glassfish/external/amx");
		ignorePackages.add("com/sun/xml/internal/bind/annotation");
		ignorePackages.add("sun/swing/icon");
		ignorePackages.add("com/sun/java/swing/plaf/windows/resources");
		ignorePackages.add("com/sun/xml/internal/ws/api/streaming");
		ignorePackages.add("sun/print");
		ignorePackages.add("com/sun/org/apache/xalan/internal/extensions");
		ignorePackages.add("sun/audio");
		ignorePackages.add("com/sun/xml/internal/ws");
		ignorePackages.add("com/sun/xml/internal/ws/transport/http/client");
		ignorePackages.add("com/oracle/net");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/transforms");
		ignorePackages.add("com/sun/jndi/ldap/pool");
		ignorePackages.add("com/sun/xml/internal/ws/policy/sourcemodel");
		ignorePackages.add("jdk/net");
		ignorePackages.add("sun/reflect/annotation");
		ignorePackages.add("com/sun/jndi/toolkit/corba");
		ignorePackages.add("sun/net/www/http");
		ignorePackages.add("com/sun/beans/util");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/utils/resolver/implementations");
		ignorePackages.add("sun/applet/resources");
		ignorePackages.add("com/sun/org/apache/xml/internal/serialize");
		ignorePackages.add("sun/misc");
		ignorePackages.add("sun/reflect/generics/parser");
		ignorePackages.add("com/sun/nio/file");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/keyresolver");
		ignorePackages.add("com/sun/xml/internal/ws/api/message/stream");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/content/keyvalues");
		ignorePackages.add("com/sun/xml/internal/ws/org/objectweb/asm");
		ignorePackages.add("sun/tools/jar");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/dtd/models");
		ignorePackages.add("com/sun/xml/internal/ws/server/provider");
		ignorePackages.add("com/sun/corba/se/spi/activation");
		ignorePackages.add("sun/security/krb5/internal/ktab");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xs");
		ignorePackages.add("sun/invoke");
		ignorePackages.add("com/sun/xml/internal/ws/api/databinding");
		ignorePackages.add("com/sun/tracing/dtrace");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/encryption");
		ignorePackages.add("sun/security/x509");
		ignorePackages.add("sun/swing");
		ignorePackages.add("com/sun/xml/internal/bind/v2/model/runtime");
		ignorePackages.add("sun/invoke/util");
		ignorePackages.add("com/sun/xml/internal/ws/api/config/management");
		ignorePackages.add("com/sun/corba/se/spi/presentation/rmi");
		ignorePackages.add("sun/swing/text");
		ignorePackages.add("com/sun/java/swing/plaf/motif");
		ignorePackages.add("sun/util/cldr");
		ignorePackages.add("com/sun/xml/internal/ws/api/message");
		ignorePackages.add("com/sun/xml/internal/ws/message/stream");
		ignorePackages.add("com/sun/xml/internal/ws/addressing/v200408");
		ignorePackages.add("com/sun/corba/se/impl/transport");
		ignorePackages.add("sun/dc/path");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/dv/util");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/msg");
		ignorePackages.add("com/sun/xml/internal/ws/developer");
		ignorePackages.add("com/sun/jndi/url/dns");
		ignorePackages.add("com/sun/xml/internal/stream/buffer/stax");
		ignorePackages.add("com/sun/org/glassfish/external/probe/provider/annotations");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xni/parser");
		ignorePackages.add("jdk/internal/util/xml");
		ignorePackages.add("com/sun/jmx/mbeanserver");
		ignorePackages.add("com/sun/xml/internal/bind/unmarshaller");
		ignorePackages.add("com/sun/org/glassfish/external/statistics/annotations");
		ignorePackages.add("com/sun/xml/internal/ws/commons/xmlutil");
		ignorePackages.add("com/oracle/webservices/internal/api");
		ignorePackages.add("com/sun/xml/internal/stream/events");
		ignorePackages.add("com/sun/org/apache/bcel/internal/generic");
		ignorePackages.add("sun/net/www/protocol/http");
		ignorePackages.add("com/sun/org/apache/xerces/internal/jaxp");
		ignorePackages.add("com/sun/xml/internal/fastinfoset/stax/factory");
		ignorePackages.add("com/sun/swing/internal/plaf/basic/resources");
		ignorePackages.add("com/sun/jndi/rmi/registry");
		ignorePackages.add("com/sun/xml/internal/ws/binding");
		ignorePackages.add("com/sun/xml/internal/ws/resources");
		ignorePackages.add("sun/awt/im");
		ignorePackages.add("com/sun/corba/se/spi/orb");
		ignorePackages.add("com/sun/org/apache/xerces/internal/xs/datatypes");
		ignorePackages.add("sun/management/jdp");
		ignorePackages.add("com/sun/corba/se/org/omg/CORBA");
		ignorePackages.add("sun/security/tools/policytool");
		ignorePackages.add("com/sun/corba/se/impl/naming/namingutil");
		ignorePackages.add("com/sun/xml/internal/ws/assembler");
		ignorePackages.add("com/sun/corba/se/impl/util");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/validation");
		ignorePackages.add("com/sun/security/sasl/digest");
		ignorePackages.add("com/sun/imageio/plugins/jpeg");
		ignorePackages.add("sun/util/logging");
		ignorePackages.add("com/sun/jmx/remote/security");
		ignorePackages.add("sun/instrument");
		ignorePackages.add("com/sun/corba/se/spi/encoding");
		ignorePackages.add("com/sun/corba/se/spi/servicecontext");
		ignorePackages.add("com/sun/org/apache/xalan/internal/utils");
		ignorePackages.add("com/sun/xml/internal/ws/api/addressing");
		ignorePackages.add("com/sun/corba/se/impl/ior");
		ignorePackages.add("sun/reflect/generics/visitor");
		ignorePackages.add("sun/tracing");
		ignorePackages.add("com/sun/jmx/snmp/internal");
		ignorePackages.add("sun/util/resources/en");
		ignorePackages.add("com/sun/corba/se/pept/protocol");
		ignorePackages.add("com/sun/corba/se/spi/orbutil/closure");
		ignorePackages.add("com/sun/xml/internal/ws/util");
		ignorePackages.add("com/sun/security/cert/internal/x509");
		ignorePackages.add("com/sun/xml/internal/ws/api/message/saaj");
		ignorePackages.add("com/sun/imageio/plugins/common");
		ignorePackages.add("com/sun/org/apache/xml/internal/dtm/ref");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/soap/impl");
		ignorePackages.add("com/sun/jmx/snmp/defaults");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/content");
		ignorePackages.add("com/sun/xml/internal/txw2/output");
		ignorePackages.add("com/sun/xml/internal/org/jvnet/fastinfoset/stax");
		ignorePackages.add("com/sun/java/swing");
		ignorePackages.add("com/sun/org/apache/xml/internal/serializer");
		ignorePackages.add("com/sun/org/apache/xml/internal/serializer/utils");
		ignorePackages.add("sun/corba");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/parser");
		ignorePackages.add("com/sun/corba/se/internal/Interceptors");
		ignorePackages.add("sun/rmi/registry");
		ignorePackages.add("sun/net/idn");
		ignorePackages.add("com/sun/org/apache/xerces/internal/impl/xs/models");
		ignorePackages.add("sun/awt/datatransfer");
		ignorePackages.add("sun/security/krb5/internal");
		ignorePackages.add("com/sun/org/apache/xml/internal/security/keys/keyresolver/implementations");
		ignorePackages.add("com/sun/xml/internal/ws/runtime/config");
		ignorePackages.add("sun/net/www/content/audio");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/dom");
		ignorePackages.add("com/sun/xml/internal/bind/v2/runtime");
		ignorePackages.add("jdk/internal/instrumentation");
		ignorePackages.add("com/sun/corba/se/spi/protocol");
		ignorePackages.add("com/sun/security/jgss");
		ignorePackages.add("com/sun/security/auth/callback");
		ignorePackages.add("com/sun/imageio/plugins/bmp");
		ignorePackages.add("com/sun/org/apache/xalan/internal/xsltc/trax");
		ignorePackages.add("sun/awt/resources");
		ignorePackages.add("com/oracle/webservices/internal/api/databinding");
		ignorePackages.add("com/sun/org/apache/xml/internal/resolver/readers");
		ignorePackages.add("sun/reflect/generics/tree");
		ignorePackages.add("com/sun/xml/internal/messaging/saaj/soap/name");
		ignorePackages.add("sun/tools/jar/resources");
		ignorePackages.add("com/sun/org/apache/xml/internal/dtm/ref/sax2dtm");
		ignorePackages.add("sun/reflect/misc");
		ignorePackages.add("com/sun/org/apache/xerces/internal/jaxp/datatype");
		ignorePackages.add("com/sun/rowset/providers");
		ignorePackages.add("com/sun/xml/internal/ws/model/soap");
		ignorePackages.add("com/sun/corba/se/spi/legacy/interceptor");
		ignorePackages.add("sun/security/action");
		ignorePackages.add("sun/font");
		ignorePackages.add("com/sun/rowset/internal");
		ignorePackages.add("com/sun/corba/se/spi/resolver");
		ignorePackages.add("com/sun/corba/se/impl/orbutil/fsm");
		ignorePackages.add("com/sun/corba/se/spi/activation/LocatorPackage");
		ignorePackages.add("com/oracle/webservices/internal/impl/internalspi/encoding");
		ignorePackages.add("com/sun/org/apache/xerces/internal/dom");
		ignorePackages.add("com/sun/corba/se/spi/ior/iiop");
		ignorePackages.add("com/sun/nio/sctp");
		ignorePackages.add("com/sun/xml/internal/ws/wsdl/writer/document/xsd");
		ignorePackages.add("sun/reflect/generics/reflectiveObjects");
	}

	protected final boolean isValidJarEntry(String name) {
		for (String string : ignorePackages) {
			if (name.contains("internal") || name.startsWith(string) || !name.endsWith(".class")
					|| name.contains("$")) {
				return false;
			}
		}
		return true;
	}

	protected final boolean isValidClass(Class<?> runtimeClass) {
		return !runtimeClass.isInterface() && (runtimeClass.getModifiers() == Modifier.PUBLIC
				|| runtimeClass.getModifiers() == Modifier.PUBLIC + Modifier.FINAL);
	}

	protected final String toUpperCase(String target) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			b.append(Character.toUpperCase(c));
		}
		return "" + b + "";
	}

	protected final String toLowerCase(String target) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			b.append(Character.toLowerCase(c));
		}
		return "" + b + "";
	}

	protected final String fromCamelCase(String target) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < target.length(); i++) {
			Character c = target.charAt(i);
			if (i > 0 && Character.isUpperCase(c)) {
				Character a = target.charAt(i - 1);
				if (a != '/') {
					b.append('_');
				}
			}
			b.append(Character.toLowerCase(c));
		}
		return "" + b + "";
	}

	protected final File createRuntimeFile(File prt, String fileName) throws IOException {
		File runtimeFile = new File(prt.getCanonicalPath() + "/prt/"
				+ fileName.replace("java", "prolog").replace("org/prolobjectlink/", "prologx/"));
		if (!runtimeFile.exists()) {
			runtimeFile.getParentFile().mkdirs();
			if (!runtimeFile.createNewFile()) {
				System.exit(1);
			}
		}
		return runtimeFile;
	}

	protected final void codingLicense(PrintWriter programmer) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		String year = f.format(new Date(System.currentTimeMillis()));
		programmer.println("% Copyright (c) " + year + " Prolobjectlink Project");
		programmer.println();
		programmer.println("% Permission is hereby granted, free of charge, to any person obtaining a copy");
		programmer.println("% of this software and associated documentation files (the \"Software\"), to deal");
		programmer.println("% in the Software without restriction, including without limitation the rights");
		programmer.println("% to use, copy, modify, merge, publish, distribute, sublicense, and/or sell");
		programmer.println("% copies of the Software, and to permit persons to whom the Software is");
		programmer.println("% furnished to do so, subject to the following conditions:");
		programmer.println();
		programmer.println("% The above copyright notice and this permission notice shall be included in");
		programmer.println("% all copies or substantial portions of the Software.");
		programmer.println();
		programmer.println("% THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR");
		programmer.println("% IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,");
		programmer.println("% FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE");
		programmer.println("% AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER");
		programmer.println("% LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,");
		programmer.println("% OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN");
		programmer.println("% THE SOFTWARE.");
		programmer.println();
	}

	protected final void codingAuthor(PrintWriter programmer) {
		programmer.println("% Author: Jose Zalacain");
		programmer.println();
	}

	protected final void codingConstants(PrintWriter programmer, Class<?> runtimeClass) {
		if (!runtimeClass.isInterface()) {
			Field[] fields = runtimeClass.getFields();
			Set<String> constants = new HashSet<String>();
			for (Field field : fields) {
				int modifier = field.getModifiers();
				if (modifier == Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL) {
					String functor = fromCamelCase(runtimeClass.getSimpleName()) + "_" + toUpperCase(field.getName());
					if (!constants.contains(functor)) {
						constants.add(functor);

						// procedure head
						PrologTerm oout = provider.newVariable("OUT", 0);
						PrologTerm head = provider.newStructure(functor, oout);

						// procedure body
						PrologTerm oclass = provider.newAtom(runtimeClass.getName());
						PrologTerm oargs = provider.newAtom(toLowerCase(field.getName()));
						PrologTerm body = provider.newStructure("object_get", oclass, oargs, oout);
						programmer.println(head + NECK + body + ".");
						programmer.println();
					}
				}
			}
		}
	}

	protected final void codingConstructors(PrintWriter programmer, Class<?> runtimeClass) {
		Constructor<?>[] cs = runtimeClass.getConstructors();
		Map<String, List<Constructor<?>>> map = new HashMap<String, List<Constructor<?>>>();
		for (Constructor<?> constructor : cs) {
			int arity = constructor.getParameters().length;
			String functor = fromCamelCase(runtimeClass.getSimpleName());
			String indicator = functor + "/" + arity;
			List<Constructor<?>> family = map.get(indicator);
			if (family == null) {
				family = new LinkedList<Constructor<?>>();
			}
			family.add(constructor);
			map.put(indicator, family);
		}

		for (Entry<String, List<Constructor<?>>> entry : map.entrySet()) {
			List<Constructor<?>> family = entry.getValue();
			for (Constructor<?> constructor : family) {
				String functor = fromCamelCase(runtimeClass.getSimpleName());
				Parameter[] parameters = constructor.getParameters();
				PrologTerm[] arguments = new PrologTerm[parameters.length + 1];
				for (int i = 0; i < parameters.length; i++) {
					String arg = toUpperCase(parameters[i].getName());
					arguments[i] = provider.newVariable(arg, i);
				}
				arguments[parameters.length] = provider.newVariable("OUT", parameters.length);

				// procedure head
				PrologTerm head = provider.newStructure(functor, arguments);

				// procedure body
				arguments = new PrologTerm[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					String arg = toUpperCase(parameters[i].getName());
					arguments[i] = provider.newVariable(arg, i);
				}
				PrologTerm oclass = provider.newAtom(runtimeClass.getName());
				PrologTerm oargs = provider.newList(arguments);
				PrologTerm oout = provider.newVariable("OUT", parameters.length);
				PrologTerm body = provider.newStructure("object_new", oclass, oargs, oout);
				programmer.println(head + NECK + body + ".");
				programmer.println();
			}
		}
	}

	protected final void codingMethods(PrintWriter programmer, Class<?> runtimeClass) {
		Method[] methods = runtimeClass.getMethods();
		Map<String, List<Method>> map = new HashMap<String, List<Method>>();
		for (Method method : methods) {
			int arity = method.getParameters().length;
			String functor = fromCamelCase(runtimeClass.getSimpleName()) + "_" + fromCamelCase(method.getName());

			String indicator = functor + "/" + arity;
			List<Method> family = map.get(indicator);
			if (family == null) {
				family = new LinkedList<Method>();
			}
			family.add(method);
			map.put(indicator, family);

		}

		for (Entry<String, List<Method>> entry : map.entrySet()) {
			List<Method> family = entry.getValue();
			for (Method method : family) {
				String functor = fromCamelCase(runtimeClass.getSimpleName()) + "_" + fromCamelCase(method.getName());
				PrologTerm[] arguments = null;
				Parameter[] parameters = method.getParameters();
				int argCount = parameters.length;

				if (method.getReturnType() == void.class) {
					arguments = new PrologTerm[argCount + 1];
				} else {
					arguments = new PrologTerm[argCount + 2];
				}

				arguments[0] = provider.newVariable("REF", 0);
				for (int i = 0; i < argCount; i++) {
					String arg = toUpperCase(parameters[i].getName());
					arguments[i + 1] = provider.newVariable(arg, i + 1);
				}

				if (method.getReturnType() != void.class) {
					arguments[argCount + 1] = provider.newVariable("OUT", argCount + 1);
				}

				// procedure head
				PrologTerm head = provider.newStructure(functor, arguments);

				// procedure body
				arguments = new PrologTerm[argCount];
				for (int i = 0; i < argCount; i++) {
					String arg = toUpperCase(parameters[i].getName());
					arguments[i] = provider.newVariable(arg, i);
				}
				PrologTerm oref = provider.newVariable("REF", 0);
				PrologTerm omethod = provider.newAtom(method.getName());
				PrologTerm oargs = provider.newList(arguments);
				PrologTerm oout = null;

				if (method.getReturnType() != void.class) {
					oout = provider.newVariable("OUT", argCount + 1);
				} else {
					oout = provider.newVariable(argCount + 1);
				}

				PrologTerm body = provider.newStructure("object_call", oref, omethod, oargs, oout);
				programmer.println(head + NECK + body + ".");
				programmer.println();
			}
		}
	}

	public final void codingRuntime(PrintWriter stdout) {
		Class<?> c = getClass();
		ProtectionDomain d = c.getProtectionDomain();
		CodeSource s = d.getCodeSource();
		String prolobjectlink = s.getLocation().getPath();
		codingRuntime(stdout, prolobjectlink);
	}

	public final void codingRuntime(PrintWriter stdout, String folder) {
		JarFile jarFile = null;
		boolean warnings = false;
		File plk = new File(folder);
		File pdk = plk.getParentFile();
		File prt = pdk.getParentFile();
		String jh = System.getProperty("java.home");
		File rt = new File(jh + "/lib/rt.jar");
		List<File> list = new ArrayList<File>();
		ClassLoader l = Thread.currentThread().getContextClassLoader();
		File[] jars = pdk.listFiles(new JarFileFilter());

		// adding rt.jar and others
		list.add(rt);
		for (File jar : jars) {
			list.add(jar);
		}
		try {

			for (File file : list) {
				stdout.println("Generating " + file);
				jarFile = new JarFile(file);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry jarEntry = entries.nextElement();
					String jarEntryName = jarEntry.getName();
					if (isValidJarEntry(jarEntryName)) {
						String className = jarEntryName.substring(0, jarEntryName.length() - 6);
						String fileName = fromCamelCase(className) + ".pl";
						try {
							Class<?> runtimeClass = l.loadClass(className.replace('/', '.'));
							if (isValidClass(runtimeClass)) {
								File runtimeFile = createRuntimeFile(prt, fileName);
								PrintWriter programmer = new PrintWriter(new FileOutputStream(runtimeFile));
								codingLicense(programmer);
								codingAuthor(programmer);
								codingInclusion(programmer, jarEntryName);
								// TODO introspection here
								codingConstants(programmer, runtimeClass);
								codingConstructors(programmer, runtimeClass);
								codingMethods(programmer, runtimeClass);
								programmer.close();
							}
						} catch (NoClassDefFoundError e) {
							stdout.println("WARNING: No definition of the class could be found " + e);
							warnings = true;
							continue;
						} catch (ClassNotFoundException e) {
							stdout.println("WARNING: No definition of the class could be found " + e);
							warnings = true;
							continue;
						}
					}
				}
				jarFile.close();
			}

		} catch (IOException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
					Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
				}
			}
		}
		if (warnings) {
			stdout.println("Generation finished with WARNINGS.");
		} else {
			stdout.println("Generation finished OK.");
		}
	}

	private class JarFileFilter implements FileFilter {

		public boolean accept(File pathname) {
			return pathname.getName().endsWith(".jar");
		}

	}

}
