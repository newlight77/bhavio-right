package io.oneprofile.right.aspect;

import io.oneprofile.right.model.Right;
import io.oneprofile.right.model.Right;

public class RightAspectUsage {

    @Rights(rights = {Right.ADMIN_READ})
    public String adminRead(String primary, String secondary) {
        return "allowedRead";
    }

    @Rights(rights = {Right.ADMIN_WRITE})
    public String adminWrite(String primary, String secondary) {
        return "allowedWrite";
    }

    @Rights(rights = {Right.ADMIN_READ, Right.ADMIN_WRITE})
    public String adminReadWrite(String primary, String secondary) {
        return "allowedReadWrite";
    }

    @Rights(rights = {Right.ADMIN_READ, Right.ADMIN_DELETE})
    public String adminReadDelete(String primary, String secondary) {
        return "allowedReadDelete";
    }
}