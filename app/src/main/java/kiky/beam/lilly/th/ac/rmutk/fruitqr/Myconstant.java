package kiky.beam.lilly.th.ac.rmutk.fruitqr;

import java.lang.ref.SoftReference;

public class Myconstant {

    private String[] favoriteFruits = {"โปรดเลือก..","กล้วย","ส้ม","มะละกอ", "แตงโม","ทุเรียน", "เงาะ", "มะม่วง", "เชอร์รี่", "สับปะรด", "ลำไย", "มังคุด", "มะนาว", "มะพร้าว","ฝรั่ง"};
    private String[] units = {"กิโลกรัม"};

    private String[] farmer = {"โปรดเลือกชื่อสวน..","สวนชูติรัตน์","สวนกวาง","สวนบางปู","สวนนาเดียร์","สวนอรทัย"};
    private String[] columnDetailProduct = {"id", "idRecord", "NameRecord", "TypeRecord", "idTypeFruit",
                                                "Name", "Detail", "Image", "Amount", "Unit", "Date", "QRcode" , "AmountPd", "UnitPd"};
    private String[] columnDetailFramer = { "id", "idRecord", "Name", "Amount", "Unit", "Date", "Dateout", "FarmerLog"};
    private String[] columnUser = {"id", "Name", "FirstName", "LastName", "Address", "Phone", "User", "Password", "TypeUser" , "AddContact"};
    private String[] columnUserFarmer = {"id", "Name", "FirstName", "LastName", "Address", "Phone", "User", "Password", "TypeUser"};



    private String urlGetDetailProductWhereQRcode = "https://www.androidthai.in.th/rmutk/getDetailWhereQRcode.php";


    private String urlGetFarmerFruitWhereId = "http://www.androidthai.in.th/rmutk/getFarmerFruitWhereId.php"; // หน้า ShowFarmer


    private String urlAddNameFruit = "http://www.androidthai.in.th/rmutk/addNameFruit.php";

    private String nameFileSharePreference = "Fruit";

    private String urlEditPasswordWhereUser = "https://www.androidthai.in.th/rmutk/editPasswordWhereUser.php";

    private String urlGetUserWhereUser = "https://www.androidthai.in.th/rmutk/getUserWhereUser.php";

    private String urlGetDetailProductWhereQR = "https://www.androidthai.in.th/rmutk/getDetailWhereQR.php";

    private String urlEditAmountWhereId = "https://www.androidthai.in.th/rmutk/editAmountWhereId.php";

    private String urlEditAmountWhereNameFruit = "https://www.androidthai.in.th/rmutk/editAmountWhereNameFruit.php";

    private String urlGetTypeTruitWhereNameFruid = "https://www.androidthai.in.th/rmutk/getTypeFruitWhereNameFruit.php";

    private String urlGetTypeFruit = "https://www.androidthai.in.th/rmutk/getAllTypeFruit.php";

    private String urlEditUserWhereId = "http://www.androidthai.in.th/rmutk/editUserWhereId.php"; //แก้ไข

    private String urlDeleteDetailFarmerWhereId = "http://www.androidthai.in.th/rmutk/deleteDatailFarmeWhereIdlilly.php"; //ลบผลผลิตออก

    private String urlgetAllDataOrderByDesc = "http://www.androidthai.in.th/rmutk/getAllDataOrderByDesc.php";

//    private String urlGetUserWhereId = "https://www.androidthai.in.th/rmutk/getUserWhereId.php";
    private String urlGetFramerWhereId = "http://androidthai.in.th/rmutk/getFarmerWhereId.php";//ดึงค่าฟามเมอร์ในรายละเอียดผลิตภัณฑ์

    private String urlGetProductWhereIdRecord = "http://www.androidthai.in.th/rmutk/getProductrWhereIdRecord.php"; //where IdRecord
    private String urlGetProductWhereId = "http://www.androidthai.in.th/rmutk/getProductrWhereId.php"; //
    private String urlGetAllDeatailProduct = "http://www.androidthai.in.th/rmutk/getDetailProduct.php";//รายละเอียดผลิตภัณฑ์
    private String urlAddDetailProduct = "http://www.androidthai.in.th/rmutk/addDetailProductLilly.php"; //เพิ่มผลิตภัณฑ์
    private String urlProductPic =  "https://www.androidthai.in.th/rmutk/Picture/product.png";


    private String urlAddDetailFarmeronly = "http://www.androidthai.in.th/rmutk/addDetailFrameronly.php"; //ผู้ใช้งาน เจ้าขอวผลผลิตเท่านั้น
    private String urlAddDetailFramer = "http://www.androidthai.in.th/rmutk/addDetailFramerLilly.php"; //เพิ่มผลผลิต
    private String urlAddUser = "https://www.androidthai.in.th/rmutk/addDataLilly.php";
    private String urlGetAllData = "https://www.androidthai.in.th/rmutk/getAllDatalilly.php";
    private String urlGetDataWhereQR = "https://www.androidthai.in.th/rmutk/getDetailWhereQRmaster.php";
    private String urlGetUserWhereId = "https://www.androidthai.in.th/rmutk/getUserWhereId.php";
    private String urlGetAllDetail = "https://www.androidthai.in.th/rmutk/getDetail.php";
    private String urlGetDetailWhereIdUser = "https://www.androidthai.in.th/rmutk/getDetailWhereIdUser.php";

    private String urlGetDetailFramerWhereIdRecord = "http://www.androidthai.in.th/rmutk/getDetailFramerWhereIdRecordLilly.php"; //ดูได้แค่ผู้ผลิค
    private String urlGetAllDetailFramer = "http://www.androidthai.in.th/rmutk/getAllDetaiFramerLilly.php"; //Admin ดูได้ทั้งหมด DetailFramer

    private String urlGetDetailFarmerWhereName = "http://www.androidthai.in.th/rmutk/getDetailFramerWhereNameLilly.php"; //ปุ่มค้นหา ให้ค้นหาชื่อผลไม้
    private String urlGetMasterWhereName = "http://www.androidthai.in.th/rmutk/getMasterWhereNameLilly.php";//ปุ่มค้นหา สวน

    private  String urlGetDetailFramerWhereIdRecordAnNameLilly = "http://www.androidthai.in.th/rmutk/getDetailFramerWhereIdRecordAnNameLilly.php";


    public String getUrlGetDetailProductWhereQRcode() {
        return urlGetDetailProductWhereQRcode;
    }

    public String[] getFarmer() {
        return farmer;
    }

    public String getUrlGetFarmerFruitWhereId() {
        return urlGetFarmerFruitWhereId;
    }

    public String getUrlAddNameFruit() {
        return urlAddNameFruit;
    }

    public String getUrlAddDetailFarmeronly() {
        return urlAddDetailFarmeronly;
    }

    public String getUrlEditPasswordWhereUser() {
        return urlEditPasswordWhereUser;
    }

    public String getUrlGetUserWhereUser() {
        return urlGetUserWhereUser;
    }

    public String getUrlGetDetailProductWhereQR() {
        return urlGetDetailProductWhereQR;
    }

    public String getUrlEditAmountWhereId() {
        return urlEditAmountWhereId;
    }

    public String getUrlEditAmountWhereNameFruit() {
        return urlEditAmountWhereNameFruit;
    }

    public String getUrlGetTypeTruitWhereNameFruid() {
        return urlGetTypeTruitWhereNameFruid;
    }

    public String getUrlGetTypeFruit() {
        return urlGetTypeFruit;
    }

    public String getUrlGetDetailFramerWhereIdRecordAnNameLilly() {
        return urlGetDetailFramerWhereIdRecordAnNameLilly;
    }

    public String getUrlGetMasterWhereName() {
        return urlGetMasterWhereName;
    }

    public String getUrlGetDetailFarmerWhereName() {
        return urlGetDetailFarmerWhereName;
    }

    public String getUrlEditUserWhereId() {
        return urlEditUserWhereId;
    }

    public String[] getColumnUserFarmer() {
        return columnUserFarmer;
    }

    public String getUrlGetProductWhereIdRecord() {
        return urlGetProductWhereIdRecord;
    }

    public String getUrlDeleteDetailFarmerWhereId() {
        return urlDeleteDetailFarmerWhereId;
    }

    public String getUrlgetAllDataOrderByDesc() {
        return urlgetAllDataOrderByDesc;
    }

    public String[] getColumnUser() {
        return columnUser;
    }

    private String urlGetAllFramer = "https://www.androidthai.in.th/rmutk/getAllFramerlilly.php";

    public String[] getColumnDetailFramer() {
        return columnDetailFramer;
    }

    public String getUrlGetFramerWhereId() {
        return urlGetFramerWhereId;
    }

    public String[] getColumnDetailProduct() {
        return columnDetailProduct;
    }

    public String getUrlGetProductWhereId() {
        return urlGetProductWhereId;
    }

    public String getUrlGetAllDeatailProduct() {
        return urlGetAllDeatailProduct;
    }

    public String getUrlAddDetailProduct() {
        return urlAddDetailProduct;
    }

    public String getUrlProductPic() {
        return urlProductPic;
    }

    public String getUrlGetAllFramer() {
        return urlGetAllFramer;
    }

    public String getUrlGetDetailFramerWhereIdRecord() {
        return urlGetDetailFramerWhereIdRecord;
    }

    public String getUrlGetAllDetailFramer() {
        return urlGetAllDetailFramer;
    }

    public String getUrlAddDetailFramer() {
        return urlAddDetailFramer;
    }

    public String[] getFavoriteFruits() {
        return favoriteFruits;
    }

    public String[] getUnits() {
        return units;
    }

    public String getNameFileSharePreference() {
        return nameFileSharePreference;
    }

    public String getUrlGetDetailWhereIdUser() {
        return urlGetDetailWhereIdUser;
    }

    public String getUrlGetAllDetail() {
        return urlGetAllDetail;
    }

    public String getUrlGetUserWhereId() {
        return urlGetUserWhereId;
    }

    public String getUrlGetDataWhereQR() {
        return urlGetDataWhereQR;
    }

    public String getUrlGetAllData() {
        return urlGetAllData;
    }

    public String getUrlAddUser() {
        return urlAddUser;
    }
}
