package supercoder79.survivalgames.game;

import xyz.nucleoid.plasmid.widget.BossBarWidget;
import xyz.nucleoid.plasmid.widget.GlobalWidgets;

import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.LiteralText;

public final class SurvivalGamesBar {
	private final BossBarWidget widget;

	private SurvivalGamesBar(BossBarWidget widget) {
		this.widget = widget;
	}

	public static SurvivalGamesBar create(GlobalWidgets widgets) {
		return new SurvivalGamesBar(widgets.addBossBar(new LiteralText("Worldborder safe! Shrinking in ..."), BossBar.Color.BLUE, BossBar.Style.PROGRESS));
	}

	public void tickSafe(long ticks, long totalTicks) {
		String time = formatTime(ticks);

		this.widget.setTitle(new LiteralText("Worldborder safe! Shrinking in " + time));
		this.widget.setProgress((float) ticks / totalTicks);
	}

	public void tickActive(long ticks, long totalTicks) {
		String time = formatTime(ticks);

		this.widget.setTitle(new LiteralText("Worldborder shrinking! Finished in " + time));
		this.widget.setProgress((float) ticks / totalTicks);
	}

	public void setFinished() {
		this.widget.setStyle(BossBar.Color.GREEN, BossBar.Style.PROGRESS);
		this.widget.setTitle(new LiteralText("Worldborder finished. Fight!"));
		this.widget.setProgress(1.0f);
	}

	public void setActive() {
		this.widget.setStyle(BossBar.Color.RED, BossBar.Style.PROGRESS);
	}

	private static String formatTime(long ticksUntil) {
		long secondsUntil = ticksUntil / 20;

		long minutes = secondsUntil / 60;
		long seconds = secondsUntil % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}
}
