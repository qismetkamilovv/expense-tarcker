package az.keytd.expensetracker.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.dto.CreateAssetRequest;
import az.keytd.expensetracker.entities.Asset;
import az.keytd.expensetracker.service.AssetService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("create")
    public Asset createAsset(@RequestBody CreateAssetRequest request) {
        return assetService.createAsset(request);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id){
        assetService.deleteAsset(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public List<Asset> findAllByOwnerId(Long ownerId){
        return assetService.findAllByOwnerId(ownerId);
    }

    @PutMapping("add")
    public Asset addAsset(Long id, Double value){
        return assetService.addAsset(id, value);
    }

}
