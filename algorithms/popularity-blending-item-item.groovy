import org.lenskit.baseline.BaselineScorer
import org.lenskit.baseline.ItemMeanRatingItemScorer
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.hybrid.*
import org.lenskit.api.ItemScorer
import org.lenskit.basic.PopularityRankItemScorer
import org.grouplens.lenskit.transform.normalize.BaselineSubtractingUserVectorNormalizer
import org.grouplens.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.knn.item.ModelSize;
import org.lenskit.knn.NeighborhoodSize;


bind ItemScorer to ItemItemScorer
bind ItemRecommender to RankBlendingItemRecommender
within (RankBlendingItemRecommender.Right, ItemRecommender) {
    bind ItemScorer to PopularityRankItemScorer
}
set BlendWeight to 0.5
bind UserVectorNormalizer to BaselineSubtractingUserVectorNormalizer
set ModelSize to 5000
set NeighborhoodSize to 20
within (UserVectorNormalizer) {
    bind (BaselineScorer, ItemScorer) to ItemMeanRatingItemScorer
}

