class UserInfoCreateModel extends UserInfoModel {

    constructor(name, nationalId, cellPhone, email, mailingAddress) {
        super(nationalId, cellPhone, email, mailingAddress);
        this.name = name;     
    }

}