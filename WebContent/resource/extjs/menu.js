/**
 * 扩展一个Menu模块，动态获取左侧菜单
 * 目前多级子菜单是通过延时循环获取的方式
 * 也可以采用点击时获取下级子菜单的方式
 * 当然，具体的情况要参照自己的需求及api返回结构
 **/

layui.define('jquery', function(exports) {
	var $ = layui.jquery;
	var menu = {
		getMenu: function(curl) {
			var that = this;
			if (!$('#side-nav').length) {
				return;
			}
			$.getJSON(curl, function(res) {
				var menuList = res.data;
				var items = [];
				var _ul = $('<ul></ul>').addClass('nav').attr('id', 'nav');
				$.each(menuList, function(index, val) {
					var item = '<li id="menu' + val.id + '"><a _href="' + val.url + '"><cite>' + val.name +
						'</cite><i class="iconfont nav_right">&#xe697;</i></a></li>';

					items.push(item);
					if (val.children && val.children.length) {
						//有二级子菜单
						setTimeout(function() {
							that.getSubMenu(val.id, val.children);
						}, 500);
					}
				});
				$('#side-nav').empty();
				_ul.append(items.join(''));
				$('#side-nav').append(_ul);
			});
		},
		/**
		 * @param {Object} id 上级子菜单id,拼接menu获取元素
		 * @param {Object} subList 子菜单列表
		 */
		getSubMenu: function(id, subList) {
			var that = this;
			var subItems = [];
			var subUl = $('<ul></ul>').addClass('sub-menu');
			$.each(subList, function(idx, sub) {
				var subItem = '<li id="menu' + sub.id + '"><a _href="' + sub.url + '"><cite>' +
					sub.name + '</cite></a></li>';
				subItems.push(subItem);
				if (sub.children && sub.children.length) {
					setTimeout(function() {
						that.getSubMenu(sub.id, sub.children);
					}, 1500);
				}
			});
			subUl.append(subItems.join(''));
			$('#menu' + id).append(subUl);
		},
		
		showMenuTree:  function(curl) {
			var that = this;
			if (!$('#menuTreeJs').length) {
				return;
			}
			$.getJSON(curl, function(res) {
				var _page_url = window.location.href;
				var _page_urlA = _page_url.split("/");
				var _page_url_search = _page_urlA[_page_urlA.length-3] + "/" + _page_urlA[_page_urlA.length-2] + "/" + _page_urlA[_page_urlA.length-1];
				var _page_url_search_flag = true;
				var _page_url_desc_fir = "";
				var _page_url_desc_sec = "";
				var _page_url_desc_thi = "";
				var menuList = res.data;
				$.each(menuList, function(index, val) {
					if (!_page_url_search_flag){ return false; }
					_page_url_desc_fir = val.name;
					if (val.url.indexOf(_page_url_search)==-1) {
						if (val.children && val.children.length) {
							$.each(val.children, function(i, v) {
								if (!_page_url_search_flag){ return false; }
								_page_url_desc_sec = " / " + v.name;
								if (v.url.indexOf(_page_url_search)==-1) {
									if (v.children && v.children.length) {
										$.each(v.children, function(ii, vv) {
											if (!_page_url_search_flag){ return false; }
											_page_url_desc_thi =  " / " +vv.name;
											if (vv.url.indexOf(_page_url_search)!=-1) {
												$("#menuTreeJs").html(_page_url_desc_fir + _page_url_desc_sec + _page_url_desc_thi);
												_page_url_search_flag = false;
											}
										});
									}
								} else {
									$("#menuTreeJs").html(_page_url_desc_fir + _page_url_desc_sec + _page_url_desc_thi);
									_page_url_search_flag = false;
								}
							});
						}
					} else {
						$("#menuTreeJs").html(_page_url_desc_fir + _page_url_desc_sec + _page_url_desc_thi);
						_page_url_search_flag = false;
					}
				});
				_page_url_desc_fir = "";
				_page_url_desc_sec = "";
				_page_url_desc_thi = "";
				_page_url_search = _page_urlA[_page_urlA.length-3] + "/" + _page_urlA[_page_urlA.length-2] + "/" + _page_urlA[_page_urlA.length-1].split("?")[0];
				$.each(menuList, function(index, val) {
					if (!_page_url_search_flag){ return false; }
					_page_url_desc_fir = val.name;
					if (val.url.indexOf(_page_url_search)==-1) {
						if (val.children && val.children.length) {
							$.each(val.children, function(i, v) {
								if (!_page_url_search_flag){ return false; }
								_page_url_desc_sec = " / " + v.name;
								if (v.url.indexOf(_page_url_search)==-1) {
									if (v.children && v.children.length) {
										$.each(v.children, function(ii, vv) {
											if (!_page_url_search_flag){ return false; }
											_page_url_desc_thi =  " / " +vv.name;
											if (vv.url.indexOf(_page_url_search)!=-1) {
												$("#menuTreeJs").html(_page_url_desc_fir + _page_url_desc_sec + _page_url_desc_thi);
												_page_url_search_flag = false;
											}
										});
									}
								} else {
									$("#menuTreeJs").html(_page_url_desc_fir + _page_url_desc_sec + _page_url_desc_thi);
									_page_url_search_flag = false;
								}
							});
						}
					} else {
						$("#menuTreeJs").html(_page_url_desc_fir + _page_url_desc_sec + _page_url_desc_thi);
						_page_url_search_flag = false;
					}
				});
			});
		}
	};
	
	/*
	 * @todo 左侧菜单事件
	 * 如果有子级就展开，没有就打开frame
	 */
	$('body').on('click', '.left-nav #nav li', function(event) {
		if($(this).children('.sub-menu').length) {
			if($(this).hasClass('open')) {
				$(this).removeClass('open');
				$(this).find('.nav_right').html('&#xe697;');
				$(this).children('.sub-menu').stop().slideUp();
				$(this).siblings().children('.sub-menu').slideUp();
			} else {
				$(this).addClass('open');
				$(this).children('a').find('.nav_right').html('&#xe6a6;');
				$(this).children('.sub-menu').stop().slideDown();
				$(this).siblings().children('.sub-menu').stop().slideUp();
				$(this).siblings().find('.nav_right').html('&#xe697;');
				$(this).siblings().removeClass('open');
			}
		} else {
			$("#_frm_cn_id").attr("src", $(this).children('a').attr('_href'));
		}
		event.stopPropagation(); //不触发任何前辈元素上的事件处理函数
	});
	
	/*
	 * @todo 重新计算iframe高度
	 */
	function FrameWH() {
		var h = $(window).height() - 164;
		$("iframe").css("height", h + "px");
	}
	$(window).resize(function() {
		FrameWH();
	});

	exports('menu', menu);
});
