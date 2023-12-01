package top.kjwang.train.business.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentSaveReq {

	/**
	 * 学生id
	 */
    private Long id;

	/**
	 * 手机号
	 */
    private String mobile;

	/**
	 * 姓名
	 */
    private String name;

	/**
	 * 年龄
	 */
    private Integer age;

	/**
	 * 电子邮箱
	 */
    private String email;

	/**
	 * 学校
	 */
    private String school;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", mobile=").append(mobile);
		sb.append(", name=").append(name);
		sb.append(", age=").append(age);
		sb.append(", email=").append(email);
		sb.append(", school=").append(school);
				sb.append("]");
		return sb.toString();
	}
}