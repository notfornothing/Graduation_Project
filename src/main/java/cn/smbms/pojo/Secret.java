package cn.smbms.pojo;

public class Secret {

    private Long id; //主键  后期要和bill的添加相等的多>(看是否  能添加) 余量是否够.
    private String providerCode;//-- 供应商编号
    private String providerName;//-- 供应商名字
    private String providerPhone;//-- 供应商电话
    private String productName;// --商品名称
    private Double total; //-- 商品剩余数量-- 这里后期用total=total-bill.productCount
    private String unit;//-- 单位productUnit 和bill一样.bill不能改了弄好先.

    @Override
    public String toString() {
        return "Secret{" +
                "id=" + id +
                ", providerCode='" + providerCode + '\'' +
                ", providerName='" + providerName + '\'' +
                ", providerPhone='" + providerPhone + '\'' +
                ", productName='" + productName + '\'' +
                ", total=" + total +
                ", unit='" + unit + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Secret(Long id, String providerCode, String providerName, String providerPhone, String productName, Double total, String unit) {
        this.id = id;
        this.providerCode = providerCode;
        this.providerName = providerName;
        this.providerPhone = providerPhone;
        this.productName = productName;
        this.total = total;
        this.unit = unit;
    }

    public Secret() {
    }

}
