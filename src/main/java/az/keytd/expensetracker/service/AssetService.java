package az.keytd.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.keytd.expensetracker.dto.CreateAssetRequest;
import az.keytd.expensetracker.entities.Asset;
import az.keytd.expensetracker.exceptions.NotFoundException;
import az.keytd.expensetracker.repository.AssetRepository;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset createAsset(CreateAssetRequest request) {
        Asset asset = new Asset();
        asset.setName(request.getName());
        asset.setAssetType(request.getAssetType());
        asset.setValue(request.getValue());
        asset.setDescription(request.getDescription());
        return assetRepository.save(asset);
    }

    public Asset getById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Asset not found"));
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

    public List<Asset> findAllByOwnerId(Long ownerId) {
       return assetRepository.findAllByOwnerId(ownerId);
    }

    public Asset addAsset(Long id, Double value) {
        Asset asset = getById(id);
        Double currentValue = asset.getValue();
        Double newValue = currentValue + value;
        asset.setValue(newValue);
        return assetRepository.save(asset);
    }

}
