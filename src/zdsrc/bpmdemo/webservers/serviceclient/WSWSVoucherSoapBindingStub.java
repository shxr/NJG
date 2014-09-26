/**
 * WSWSVoucherSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.kingdee.eas.bpmdemo.webservers.serviceclient;

public class WSWSVoucherSoapBindingStub extends org.apache.axis.client.Stub implements com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucherSrvProxy {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("importVoucher");
        oper.addParameter(new javax.xml.namespace.QName("", "voucherCols"), new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOf_tns1_WSWSVoucher"), com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[].class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "isTempSave"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "isVerify"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "hasCashflow"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOf_tns2_WSWSRtnInfo"));
        oper.setReturnClass(com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "importVoucherReturn"));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "fault"),
                      "com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException",
                      new javax.xml.namespace.QName("urn:client.wsvoucher", "WSInvokeException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("importVoucher");
        oper.addParameter(new javax.xml.namespace.QName("", "voucherCols"), new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOf_tns1_WSWSVoucher"), com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[].class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "isVerify"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "isImpCashflow"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOfArrayOf_xsd_string"));
        oper.setReturnClass(java.lang.String[][].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "importVoucherReturn"));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "fault"),
                      "com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException",
                      new javax.xml.namespace.QName("urn:client.wsvoucher", "WSInvokeException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("importVoucherOfReturnID");
        oper.addParameter(new javax.xml.namespace.QName("", "voucherCols"), new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOf_tns1_WSWSVoucher"), com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[].class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "isVerify"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.addParameter(new javax.xml.namespace.QName("", "isImpCashflow"), new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOfArrayOf_xsd_string"));
        oper.setReturnClass(java.lang.String[][].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "importVoucherOfReturnIDReturn"));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "fault"),
                      "com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException",
                      new javax.xml.namespace.QName("urn:client.wsvoucher", "WSInvokeException"), 
                      true
                     ));
        _operations[2] = oper;

    }

    public WSWSVoucherSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WSWSVoucherSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WSWSVoucherSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOf_tns2_WSWSRtnInfo");
            cachedSerQNames.add(qName);
            cls = com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOfArrayOf_xsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[][].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("urn:client.wsvoucher", "WSInvokeException");
            cachedSerQNames.add(qName);
            cls = com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://10.130.12.34:7888/ormrpc/services/WSWSVoucher", "ArrayOf_tns1_WSWSVoucher");
            cachedSerQNames.add(qName);
            cls = com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://app.gl.fi.eas.kingdee.com", "WSWSRtnInfo");
            cachedSerQNames.add(qName);
            cls = com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:client.wsvoucher", "WSWSVoucher");
            cachedSerQNames.add(qName);
            cls = com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:client.wsvoucher", "WSBean");
            cachedSerQNames.add(qName);
            cls = com.kingdee.eas.bpmdemo.webservers.serviceclient.WSBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call =
                    (org.apache.axis.client.Call) super.service.createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                        java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                        _call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
        }
    }

    public com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo[] importVoucher(com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[] voucherCols, boolean isTempSave, boolean isVerify, boolean hasCashflow) throws java.rmi.RemoteException, com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.app.gl.fi.eas.kingdee.com", "importVoucher"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {voucherCols, new java.lang.Boolean(isTempSave), new java.lang.Boolean(isVerify), new java.lang.Boolean(hasCashflow)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSRtnInfo[].class);
            }
        }
    }

    public java.lang.String[][] importVoucher(com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[] voucherCols, int isVerify, int isImpCashflow) throws java.rmi.RemoteException, com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.app.gl.fi.eas.kingdee.com", "importVoucher"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {voucherCols, new java.lang.Integer(isVerify), new java.lang.Integer(isImpCashflow)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[][]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[][]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[][].class);
            }
        }
    }

    public java.lang.String[][] importVoucherOfReturnID(com.kingdee.eas.bpmdemo.webservers.serviceclient.WSWSVoucher[] voucherCols, int isVerify, int isImpCashflow) throws java.rmi.RemoteException, com.kingdee.eas.bpmdemo.webservers.serviceclient.WSInvokeException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.app.gl.fi.eas.kingdee.com", "importVoucherOfReturnID"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {voucherCols, new java.lang.Integer(isVerify), new java.lang.Integer(isImpCashflow)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[][]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[][]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[][].class);
            }
        }
    }

}