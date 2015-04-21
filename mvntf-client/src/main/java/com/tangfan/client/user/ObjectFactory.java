
package com.tangfan.client.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tangfan.client.user package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BinaryResponse_QNAME = new QName("http://www.tangfan.org/user", "binaryResponse");
    private final static QName _Login_QNAME = new QName("http://www.tangfan.org/user", "login");
    private final static QName _UploadResponse_QNAME = new QName("http://www.tangfan.org/user", "uploadResponse");
    private final static QName _Delete_QNAME = new QName("http://www.tangfan.org/user", "delete");
    private final static QName _LicenseInfo_QNAME = new QName("http://www.tangfan.org/user", "licenseInfo");
    private final static QName _ListResponse_QNAME = new QName("http://www.tangfan.org/user", "listResponse");
    private final static QName _DeleteResponse_QNAME = new QName("http://www.tangfan.org/user", "deleteResponse");
    private final static QName _Upload_QNAME = new QName("http://www.tangfan.org/user", "upload");
    private final static QName _Add_QNAME = new QName("http://www.tangfan.org/user", "add");
    private final static QName _List_QNAME = new QName("http://www.tangfan.org/user", "list");
    private final static QName _UserException_QNAME = new QName("http://www.tangfan.org/user", "UserException");
    private final static QName _LoginResponse_QNAME = new QName("http://www.tangfan.org/user", "loginResponse");
    private final static QName _Binary_QNAME = new QName("http://www.tangfan.org/user", "binary");
    private final static QName _AddResponse_QNAME = new QName("http://www.tangfan.org/user", "addResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tangfan.client.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Binary }
     * 
     */
    public Binary createBinary() {
        return new Binary();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link UserException }
     * 
     */
    public UserException createUserException() {
        return new UserException();
    }

    /**
     * Create an instance of {@link List }
     * 
     */
    public List createList() {
        return new List();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link Upload }
     * 
     */
    public Upload createUpload() {
        return new Upload();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link LicenseInfo }
     * 
     */
    public LicenseInfo createLicenseInfo() {
        return new LicenseInfo();
    }

    /**
     * Create an instance of {@link ListResponse }
     * 
     */
    public ListResponse createListResponse() {
        return new ListResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link UploadResponse }
     * 
     */
    public UploadResponse createUploadResponse() {
        return new UploadResponse();
    }

    /**
     * Create an instance of {@link BinaryResponse }
     * 
     */
    public BinaryResponse createBinaryResponse() {
        return new BinaryResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "binaryResponse")
    public JAXBElement<BinaryResponse> createBinaryResponse(BinaryResponse value) {
        return new JAXBElement<BinaryResponse>(_BinaryResponse_QNAME, BinaryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "uploadResponse")
    public JAXBElement<UploadResponse> createUploadResponse(UploadResponse value) {
        return new JAXBElement<UploadResponse>(_UploadResponse_QNAME, UploadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LicenseInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "licenseInfo")
    public JAXBElement<LicenseInfo> createLicenseInfo(LicenseInfo value) {
        return new JAXBElement<LicenseInfo>(_LicenseInfo_QNAME, LicenseInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "listResponse")
    public JAXBElement<ListResponse> createListResponse(ListResponse value) {
        return new JAXBElement<ListResponse>(_ListResponse_QNAME, ListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Upload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "upload")
    public JAXBElement<Upload> createUpload(Upload value) {
        return new JAXBElement<Upload>(_Upload_QNAME, Upload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "list")
    public JAXBElement<List> createList(List value) {
        return new JAXBElement<List>(_List_QNAME, List.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "UserException")
    public JAXBElement<UserException> createUserException(UserException value) {
        return new JAXBElement<UserException>(_UserException_QNAME, UserException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Binary }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "binary")
    public JAXBElement<Binary> createBinary(Binary value) {
        return new JAXBElement<Binary>(_Binary_QNAME, Binary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tangfan.org/user", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

}
