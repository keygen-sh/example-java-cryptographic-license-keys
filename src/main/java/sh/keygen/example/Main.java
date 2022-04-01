package sh.keygen.example;

import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.util.encoders.Hex;
import java.math.BigInteger;
import java.util.Base64;

public class Main {
  public static void main(String args[]) {
    String licenseKey = "key/eyJhY2NvdW50Ijp7ImlkIjoiMWZkZGNlYzgtOGRkMy00ZDhkLTliMTYtMjE1Y2FjMGY5YjUyIn0sInByb2R1Y3QiOnsiaWQiOiIxZjA4NmVjOS1hOTQzLTQ2ZWEtOWRhNC1lNjJjMjE4MGMyZjQifSwicG9saWN5Ijp7ImlkIjoiMjlkOWQ4ZDMtYmYxZC00NGQxLWExYjktODIwNDQwZDQyMmZmIiwiZHVyYXRpb24iOm51bGx9LCJ1c2VyIjpudWxsLCJsaWNlbnNlIjp7ImlkIjoiZjNkNjU3ZGUtMmI1MC00YWRmLTg2N2ItZWYwM2RjODZhM2ZlIiwiY3JlYXRlZCI6IjIwMjEtMTAtMTRUMTU6MjA6MzIuNjcwWiIsImV4cGlyeSI6IjIwMjEtMTAtMTVUMDA6MDA6MDAuMDAwWiJ9fQ==.Z_fDBaVqmBxHWkzi_TCPWGOrE0rItN_xEFdc8TtR0ahB-Gx84S6r4pXPuTxeIREtLCVJt3lcFy_WuCNeCOFRAA==";
    String publicKey = "e8601e48b69383ba520245fd07971e983d06d22c4257cfd82304601479cee788";

    // Parse signed license key
    String signingData = licenseKey.split("\\.", 2)[0];
    String encodedSignature = licenseKey.split("\\.", 2)[1];
    String signingPrefix = signingData.split("/", 2)[0];
    String encodedDataset = signingData.split("/", 2)[1];

    // Verify signing prefix
    if (!signingPrefix.equals("key")) {
      System.out.println("Unsupported signing prefix");

      return;
    }

    // Convert hex-encoded public key to a byte array
    byte[] publicKeyBytes = Hex.decode(publicKey);

    // Decode base64 signature and signing data to byte arrays
    byte[] signatureBytes = Base64.getUrlDecoder().decode(encodedSignature);
    byte[] signingDataBytes = signingData.getBytes();

    // Set up Ed25519 verifier
    Ed25519PublicKeyParameters params = new Ed25519PublicKeyParameters(publicKeyBytes, 0);
    Ed25519Signer verifier = new Ed25519Signer();

    verifier.init(false, params);
    verifier.update(signingDataBytes, 0, signingDataBytes.length);

    // Verify the signature
    boolean ok = verifier.verifySignature(signatureBytes);
    if (ok) {
      // Decode base64 dataset to a string
      byte[] datasetBytes = Base64.getUrlDecoder().decode(encodedDataset);
      String dataset = new String(datasetBytes);

      System.out.println("License key is valid!");
      System.out.println(
        String.format("> Dataset: %s", dataset)
      );
    } else {
      System.out.println("License key is invalid!");
    }
  }
}
