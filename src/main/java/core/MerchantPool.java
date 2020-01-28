package core;

import dtupay.DtuPayMerchantRepresentation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MerchantPool
{
    private static Map<String, DtuPayMerchantRepresentation> merchants = new HashMap<>();

    public static DtuPayMerchantRepresentation get(String uuid)
    {
        return merchants.get(uuid);
    }

    public static Collection<DtuPayMerchantRepresentation> getAll()
    {
        return merchants.values();
    }

    public static boolean create(DtuPayMerchantRepresentation m)
    {
        if (!merchants.containsKey(m.getUuid()))
        {
            merchants.put(m.getUuid(), m);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean update(DtuPayMerchantRepresentation m)
    {
        if (!merchants.containsKey(m.getUuid()))
        {
            return false;
        }
        else
        {
            merchants.replace(m.getUuid(), m);
            return true;
        }
    }

    public static boolean delete(String uuid)
    {
        if (!merchants.containsKey(uuid))
        {
            return false;
        }
        else
        {
            merchants.remove(uuid);
            return true;
        }
    }
}
