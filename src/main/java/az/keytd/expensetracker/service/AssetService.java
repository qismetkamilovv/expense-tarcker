package az.keytd.expensetracker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.entities.Asset;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.AssetRepository;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset createAsset(String name, String assetType, Double value, String description) {
        Asset asset = new Asset();
        asset.setName(name);
        asset.setAssetType(assetType);
        asset.setValue(value);
        asset.setDescription(description);
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
    //TODO create method getallbyownerID
    //TODO change name to id
    public Asset addAsset(String name, Double value) {
        //TODO extract to getbyid method
        Asset asset = assetRepository.findByName(name)
            .orElseThrow(() -> new NotFoundException("Asset is not existed"));
        Double currentValue = asset.getValue();
        Double newValue = currentValue + value ;
        asset.setValue(newValue);
        return assetRepository.save(asset);
    }

}
