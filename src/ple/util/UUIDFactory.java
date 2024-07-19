package ple.util;

import java.security.SecureRandom;
import java.util.UUID;

import ple.exceptions.customexceptions.PleException;
import ple.exceptions.exceptiontypes.UtilErrorType;

public class UUIDFactory {
	private final SecureRandom secRand = new SecureRandom();
	private final byte[] secRandBuf16 = new byte[16];
	private final byte[] secRandBuf8 = new byte[8];
	private static UUIDFactory instance = new UUIDFactory();

	private UUIDFactory() {
	}

	public static UUIDFactory getInstance() {
		if (instance == null) {
			instance = new UUIDFactory();
		}
		return instance;
	}
	
	/**
	 * uuid 생성
	 * @return
	 * @throws PleException 
	 */
	public UUID createNewUUID() throws PleException {
		this.secRand.nextBytes(this.secRandBuf16);
		byte[] var10000 = this.secRandBuf16;
		var10000[6] = (byte) (var10000[6] & 15);
		var10000 = this.secRandBuf16;
		var10000[6] = (byte) (var10000[6] | 64);
		var10000 = this.secRandBuf16;
		var10000[8] = (byte) (var10000[8] & 63);
		var10000 = this.secRandBuf16;
		var10000[8] = (byte) (var10000[8] | 128);
		var10000 = this.secRandBuf16;
		var10000[10] = (byte) (var10000[10] | 128);
		long mostSig = 0L;

		for (int i = 0; i < 8; ++i) {
			mostSig = mostSig << 8 | (long) (this.secRandBuf16[i] & 255);
		}

		long leastSig = 0L;

		for (int i = 8; i < 16; ++i) {
			leastSig = leastSig << 8 | (long) (this.secRandBuf16[i] & 255);
		}
		
		UUID newUuid = new UUID(mostSig, leastSig);
		
		if (this.isValidUUID(newUuid.toString())) {
			return newUuid;
		}
		
		throw new PleException(UtilErrorType.UUIDBuildingError, "inValidUuid - " + newUuid);
	}

	private boolean isValidUUID(String uuid) {
		boolean isValid = true;
		int len = uuid.length();
		String hexDigits = "0123456789abcdef";

		for (int i = 0; i < len; ++i) {
			char c = uuid.charAt(i);
			if (i != 8 && i != 13 && i != 18 && i != 23) {
				if (hexDigits.indexOf(c) == -1) {
					isValid = false;
					break;
				}
			} else if (c != '-') {
				isValid = false;
				break;
			}
		}

		return isValid;
	}

	/*
	private static void printUsage() {
		System.err.println("...UUIDFactory [help] cnt=<number of uuids required>");
		System.exit(-1);
	}

	public static void main(String[] args) {
		int cnt = 1;

		for (int i = 0; i < args.length; ++i) {
			if (args[i].equalsIgnoreCase("help")) {
				printUsage();
			} else if (args[i].startsWith("cnt=")) {
				cnt = Integer.parseInt(args[i].substring(4, args[i].length()));
			} else {
				System.err.println("Unknown parameter: '" + args[i] + "' at position " + i);
				if (i > 0) {
					System.err.println("Last valid parameter was '" + args[i - 1] + "'");
				}

				printUsage();
			}
		}

		UUIDFactory uf = getInstance();

		for (int i = 0; i < cnt; ++i) {
			UUID id = uf.newUUID();
			System.out.println("new UUID : " + id.toString());
		}

	}

*/

}