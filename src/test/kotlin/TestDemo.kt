import org.dif.model.EncodingType
import org.dif.model.PublicKeyAgreement
import org.dif.model.PublicKeyAuthentication
import org.dif.model.PublicKeyTypeAgreement
import org.dif.model.PublicKeyTypeAuthentication
import org.dif.peerdid.createPeerDIDNumalgo0
import org.dif.peerdid.createPeerDIDNumalgo2
import org.dif.peerdid.resolvePeerDID
import kotlin.test.Test

class TestDemo {
    @Test
    fun testCreateResolvePeerDID() {
        val encryptionKeys = listOf(
            PublicKeyAgreement(
                type = PublicKeyTypeAgreement.X25519,
                encodingType = EncodingType.BASE58,
                encodedValue = "DmgBSHMqaZiYqwNMEJJuxWzsGGC8jUYADrfSdBrC6L8s",
            )
        )
        val signingKeys = listOf(
            PublicKeyAuthentication(
                type = PublicKeyTypeAuthentication.ED25519,
                encodingType = EncodingType.BASE58,
                encodedValue = "ByHnpUCFb1vAfh9CFZ8ZkmUZguURW8nSw889hy6rD8L7",
            )
        )
        val service = """
            {
                "type": "didcommmessaging",
                "serviceEndpoint": "https://example.com/endpoint",
                "routingKeys": ["did:example:somemediator#somekey"]
            }
        """

        val peerDIDAlgo0 = createPeerDIDNumalgo0(signingKeys[0])
        val peerDIDAlgo2 = createPeerDIDNumalgo2(
            encryptionKeys, signingKeys, service
        )

        println("peer_did_algo_0:" + peerDIDAlgo0)
        println("==================================")
        println("peer_did_algo_2:" + peerDIDAlgo2)
        println("==================================")

        val DIDDocAlgo0 = resolvePeerDID(peerDIDAlgo0)
        val DIDDocAlgo2 = resolvePeerDID(peerDIDAlgo2)
        println("did_doc_algo_0:" + DIDDocAlgo0)
        println("==================================")
        print("did_doc_algo_2:" + DIDDocAlgo2)
    }
}