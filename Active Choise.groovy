
            import com.amazonaws.client.builder.AwsClientBuilder;
            import com.amazonaws.services.ecr.AmazonECR;
            import com.amazonaws.services.ecr.AbstractAmazonECR;
            import com.amazonaws.services.ecr.AmazonECRClient;
            import com.amazonaws.services.ecr.model.ListImagesRequest;
            import com.amazonaws.services.ecr.model.ListImagesResult;
            import com.amazonaws.services.ecr.AmazonECRClientBuilder;
            import com.amazonaws.regions.Region;
            import com.amazonaws.regions.RegionUtils;
            import com.amazonaws.regions.Regions;
            import jenkins.model.*
 
            AmazonECR client = AmazonECRClientBuilder.standard().withRegion("us-east-1").build();
            ListImagesRequest request = new ListImagesRequest().withRepositoryName("REPO NAME");
            res = client.listImages(request);
 
 
            def result = []
            for (image in res) {
               result.add(image.getImageIds());
            }
 
            return result[0].imageTag;
