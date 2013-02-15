package games.ghoststories.data.interfaces;

import games.ghoststories.enums.EColor;

public interface IGameTokenListener {
   public void taoTokensUpdated(EColor pColor, int pNumTokens);
   public void qiTokensUpdated(int pNumTokens);
}
