package cn.smbms.pojo;


public class Repo {

  private Long id; //主键  后期要和bill的添加相等的多>(看是否  能添加) 余量是否够.
  private Double total; //-- 商品剩余数量-- 这里后期用total=total-bill.productCount
  private String unit;//-- productUnit 和bill一样.bill不能改了弄好先.
  private String providerName;//-- 供应商名字
  private String providerCode;//-- 供应商编号
  private String billName;

  @Override
  public String toString() {
    return "Repo{" +
            "id=" + id +
            ", total=" + total +
            ", unit='" + unit + '\'' +
            ", providerName='" + providerName + '\'' +
            ", providerCode='" + providerCode + '\'' +
            ", billName='" + billName + '\'' +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public String getBillName() {
    return billName;
  }

  public void setBillName(String billName) {
    this.billName = billName;
  }

  public Repo() {
  }

  public Repo(Long id, Double total, String unit, String providerName, String providerCode, String billName) {
    this.id = id;
    this.total = total;
    this.unit = unit;
    this.providerName = providerName;
    this.providerCode = providerCode;
    this.billName = billName;
  }
}
