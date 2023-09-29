package picasso.server.api.exchange.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostCreatePaymentRequest {
  private Boolean payResult;
  private Long userId;
  private String merchantUid;
  private String productName;
  private String pgProvider;
  private Long paidAmount;
}
