package com.sfc.executor.pub.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfc.executor.pub.utils.JsonObj;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RspsBaseMsg extends JsonObj {
	public static final String BIZ_CODE_00000_SUCCESS = "00000";
	public static final String BIZ_CODE_F0021_NO_JWT_HEADER = "F0021";
	public static final String BIZ_CODE_F0022_JWT_NO_BEARER_PFX = "F0022";
	public static final String BIZ_CODE_F0023_JWT_SPLIT_NE3 = "F0023";
	public static final String BIZ_CODE_F0024_JWT_SIGNATURE_NE = "F0024";
	public static final String BIZ_CODE_F0025_JWT_PAYLOAD_DECODE_FAILED = "F0025";
	public static final String BIZ_CODE_F0026_JWT_EXPIRED = "F0026";
	public static final String BIZ_CODE_F0029_JWT_VALIDATE_EXCEPTION = "F0029";
	public static final String BIZ_CODE_F0031_NO_JWT_PAYLOAD = "F0031";
	public static final String BIZ_CODE_F0032_JWT_PAYLOAD_LACK_GUTS = "F0032";
	public static final String BIZ_CODE_F0033_JWT_PAYLOAD_CLI_MISMATCH = "F0033";

	private String bizCode;
	private String bizDesc;

	public RspsBaseMsg() {
		this.bizCode = BIZ_CODE_00000_SUCCESS;
		this.bizDesc = "success";
	}

	@JsonIgnore
	public boolean is00000() {
		return BIZ_CODE_00000_SUCCESS.equals(this.bizCode) ? true : false;
	}
}
