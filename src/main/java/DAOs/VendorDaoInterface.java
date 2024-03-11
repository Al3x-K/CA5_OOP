package DAOs;

import DTOs.Vendor;
import Exceptions.DaoException;
import java.util.List;

public interface VendorDaoInterface
{
    public List<Vendor> getAllVendors() throws DaoException;

    public Vendor getVendorById(int id) throws DaoException;

    public void deleteVendor(int vendorId) throws DaoException;

}