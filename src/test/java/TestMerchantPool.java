import core.MerchantPool;
import dtupay.DtuPayMerchantRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMerchantPool
{
    private DtuPayMerchantRepresentation merchant =
            new DtuPayMerchantRepresentation("Merchant", "1234", "4321");

    @BeforeEach
    public void initiate()
    {
        MerchantPool.getAll().clear();
        MerchantPool.create(merchant);
    }

    @DisplayName("Test MerchantPool.create()")
    @Test
    public void createMerchant()
    {
        boolean isCreated = MerchantPool.getAll().contains(merchant);
        assertTrue(isCreated);
    }

    @DisplayName("Test MerchantPool.getAll().contains")
    @Test
    public void getMerchant()
    {
        boolean merchantExists = MerchantPool.getAll().contains(merchant);
        assertTrue(merchantExists);
    }

    @DisplayName("Test MerchantPool.update()")
    @Test
    public void updateMerchant()
    {
        String newName = "Cool new Merchant Name";
        merchant.setName(newName);
        boolean isUpdated = MerchantPool.update(merchant);
        assertTrue(isUpdated);
        assertEquals(newName, merchant.getName());
    }

    @DisplayName("Test MerchantPool.delete()")
    @Test
    public void deleteMerchant()
    {
        boolean isDeleted = MerchantPool.delete(merchant.getUuid());
        assertTrue(isDeleted);
    }
}
