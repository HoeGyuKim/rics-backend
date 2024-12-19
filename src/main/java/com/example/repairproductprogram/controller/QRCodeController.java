package com.example.repairproductprogram.controller;

import com.example.repairproductprogram.Util.AESUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    // 비밀키 생성 및 문자열로 변환하여 저장 (실제 애플리케이션에서는 안전하게 저장해야 함)
    private static final SecretKey secretKey;
    private static final String encodedSecretKey;

    static {
        try {
            secretKey = AESUtil.generateSecretKey();
            encodedSecretKey = AESUtil.encodeKey(secretKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate AES key", e);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateEncryptedQRCode(@PathVariable String id) {
        try {
            // ID 암호화
            String encryptedId = AESUtil.encrypt(id, secretKey);

            // QR 코드 생성
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(encryptedId, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            // 이미지 데이터를 ResponseEntity로 반환
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(pngData, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

